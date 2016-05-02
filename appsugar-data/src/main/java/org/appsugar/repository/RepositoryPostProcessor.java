package org.appsugar.repository;

import java.util.Arrays;

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
				context.getBeansOfType(IdEntitySpecification.class).entrySet().stream()
						.filter(entry -> Arrays.equals(classes, GenericTypeResolver
								.resolveTypeArguments(entry.getValue().getClass(), IdEntitySpecification.class)))
						.forEach(entry -> {
							target.specification = entry.getValue();
						});
				if (target.specification == null) {
					throw new BeanCreationException(
							"No specification to be found by condition " + classes[1] + " repository name " + beanName);
				}
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
