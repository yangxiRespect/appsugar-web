package org.appsugar.test.spring;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.google.common.collect.Lists;

/**
 * spring测试基类
 * @author NewYoung
 * 2016年11月7日下午1:32:10
 */
public class SpringBaseTest extends AbstractJUnit4SpringContextTests {

	@SuppressWarnings("hiding")
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 查找组件
	 * @param superClass 组件类型
	 * @param genericTypes 泛型
	 */
	@SuppressWarnings("unchecked")
	public static <T> T findComponent(ApplicationContext context, Class<? super T> superClass,
			Class<?>... genericTypes) {
		List<Class<?>> genericTypeList = Lists.newArrayList(genericTypes);
		return (T) context.getBeansOfType(superClass).values().stream().filter(e -> {
			return Lists.newArrayList(GenericTypeResolver.resolveTypeArguments(e.getClass(), superClass))
					.containsAll(genericTypeList);
		}).findFirst().get();
	}

}
