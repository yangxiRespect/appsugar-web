package org.appsugar.data.common.repository.querydsl;

import java.util.List;
import java.util.stream.Collectors;

import org.appsugar.data.common.repository.GenericIdEntityRepository;
import org.appsugar.data.common.repository.ext.RepositoryExtension;
import org.appsugar.data.common.repository.ext.RepositoryExtensionQueryDslImpl;
import org.appsugar.data.common.repository.ext.RepositoryExtensionable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.Ordered;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;

/** 
 * 
 * 解决jpa不支持base Repository 注入问题
 * @author NewYoung
 *
 */
@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
public class QuerydslSpecificationRepositoryPostProcessor implements BeanPostProcessor, Ordered {

	private static final Logger logger = LoggerFactory.getLogger(QuerydslSpecificationRepositoryPostProcessor.class);

	@Autowired
	private ApplicationContext context;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		Class<? extends GenericIdEntityRepository> baseRepositoryClass = getBaseRepositoryClass();
		if (AopUtils.isAopProxy(bean) && baseRepositoryClass.isAssignableFrom(bean.getClass())) {
			try {
				Advised advised = (Advised) bean;
				Object target = advised.getTargetSource().getTarget();
				//如果repository没有实现 可增强接口.或者没有实现QueryDslPredicateExecutor  那么不做任何处理
				if (!(target instanceof RepositoryExtensionable) || !(target instanceof QueryDslPredicateExecutor)) {
					return bean;
				}
				Class<?> repositoryInterface = getRepositoryClass(baseRepositoryClass, advised.getProxiedInterfaces());
				logger.debug("prepar to inject repository extension {}", repositoryInterface);
				injectRepositoryExtension(repositoryInterface, (RepositoryExtensionable) target);
			} catch (Exception e) {
				throw new BeanCreationException(beanName, "jpa data base repository implements enhance fatal", e);
			}
		}
		return bean;
	}

	/**
	 * 查询到对应的增强实现并注入
	 * @author NewYoung
	 * 2016年11月24日上午10:42:16
	 */
	protected void injectRepositoryExtension(Class<?> repositoryInterface, RepositoryExtensionable target) {
		Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(repositoryInterface,
				GenericIdEntityRepository.class);
		Class<?> entityClass = classes[1];
		Class<?> conditionClass = classes[2];
		for (Class<? extends QueryDslSpecification> specificationClass : getOrderedSatisfySpecificationClass()) {
			if (injectRepositoryExtension(repositoryInterface, conditionClass, target, specificationClass,
					entityClass)) {
				return;
			}
		}
		throw new RuntimeException("Did not match any QuerydslSpecification for " + repositoryInterface);
	}

	protected boolean injectRepositoryExtension(Class<?> repositoryInterface, Class<?> conditionClass,
			RepositoryExtensionable target, Class<? extends QueryDslSpecification> specificationClass,
			Class<?> enttiyClass) {
		//查找符合条件的queryDslSpecification
		List<? extends QueryDslSpecification> specificationList = context.getBeansOfType(QueryDslSpecification.class)
				.values().stream().filter(e -> {
					Class<?>[] specificationGenericTypes = GenericTypeResolver.resolveTypeArguments(e.getClass(),
							QueryDslSpecification.class);
					return specificationGenericTypes[0].equals(conditionClass);
				}).collect(Collectors.toList());
		if (CollectionUtils.isEmpty(specificationList)) {
			return false;
		}
		if (specificationList.size() > 1) {
			throw new RuntimeException(" More than one QueryDslSpecification founded " + specificationList + " for "
					+ repositoryInterface);
		}
		target.setRepositoryExtension(createRepositoryExtensionable((QueryDslPredicateExecutor) target,
				specificationList.get(0), enttiyClass));
		return true;
	}

	/**
	 * 创建数据访问接口增强实现
	 * @author NewYoung
	 * 2016年11月24日上午11:28:55
	 */
	protected RepositoryExtension createRepositoryExtensionable(QueryDslPredicateExecutor repository,
			QueryDslSpecification specification, Class<?> entityClass) {
		return new RepositoryExtensionQueryDslImpl(repository, specification, entityClass);
	}

	/**
	 * 获取按顺序匹配符合条件的Specification class
	 * @author NewYoung
	 * 2016年11月24日上午11:07:48
	 */
	protected List<Class<? extends QueryDslSpecification>> getOrderedSatisfySpecificationClass() {
		return Lists.newArrayList(QueryDslSpecification.class);
	}

	/**
	 * 获取需要增强的基类接口
	 * @author NewYoung
	 * 2016年11月24日上午11:03:21
	 */
	protected Class<? extends GenericIdEntityRepository> getBaseRepositoryClass() {
		return GenericIdEntityRepository.class;
	}

	/**
	 * 获取baseRepositoryClazz 子类
	 * @author NewYoung
	 * 2016年11月24日上午11:03:00
	 */
	protected Class<?> getRepositoryClass(Class<?> baseRepositoryClazz, Class<?>... interfaces) {
		for (Class<?> clazz : interfaces) {
			if (baseRepositoryClazz.isAssignableFrom(clazz)) {
				return clazz;
			}
		}
		return null;
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE;
	}

}
