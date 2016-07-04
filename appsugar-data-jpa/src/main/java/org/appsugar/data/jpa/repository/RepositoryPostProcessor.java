package org.appsugar.data.jpa.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.appsugar.specification.IdEntitySpecification;
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

/** 
 * 
 * 解决jpa不支持base Repository 注入问题
 * @author NewYoung
 *
 */
public class RepositoryPostProcessor implements BeanPostProcessor, Ordered {

	private static final Logger logger = LoggerFactory.getLogger(RepositoryPostProcessor.class);

	@Autowired
	private ApplicationContext context;

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (AopUtils.isAopProxy(bean) && bean instanceof IdEntityRepository) {
			logger.debug("Found repository {} prepare to  set Specifiction ", beanName);
			try {
				Advised advised = (Advised) bean;
				Class<?> repositoryInterface = getRepositoryClass(IdEntityRepository.class,
						advised.getProxiedInterfaces());
				Class<?>[] classes = GenericTypeResolver.resolveTypeArguments(repositoryInterface,
						IdEntityRepository.class);
				IdEntityRepositoryImpl target = (IdEntityRepositoryImpl) advised.getTargetSource().getTarget();
				List<IdEntitySpecification> matchedSpecifictionList = new LinkedList<>();
				for (Entry<String, IdEntitySpecification> entry : context.getBeansOfType(IdEntitySpecification.class)
						.entrySet()) {
					if (classes[1] == GenericTypeResolver.resolveTypeArguments(entry.getValue().getClass(),
							IdEntitySpecification.class)[1]) {
						matchedSpecifictionList.add(entry.getValue());
					}
				}
				if (matchedSpecifictionList.size() > 1) {
					throw new BeanCreationException("More than one specification to be found by condition " + classes[1]
							+ " repository name " + beanName + " matched " + matchedSpecifictionList);
				}
				if (matchedSpecifictionList.size() == 0) {
					throw new BeanCreationException(
							"No specification to be found by condition " + classes[1] + " repository name " + beanName);
				}
				target.specification = matchedSpecifictionList.get(0);
			} catch (BeansException e) {
				throw e;
			} catch (Exception e) {
				throw new BeanCreationException(beanName, "jpa data base repository implements enhance fatal", e);
			}
		}
		return bean;
	}

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
