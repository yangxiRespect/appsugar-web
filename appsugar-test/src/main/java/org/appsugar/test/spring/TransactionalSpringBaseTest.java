package org.appsugar.test.spring;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * 带事物的测试基类
 * @author NewYoung
 * 2016年11月7日下午1:30:58
 */
public class TransactionalSpringBaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 查找组件
	 * @param superClass 组件类型
	 * @param genericTypes 泛型
	 */
	public static <T> T findComponent(ApplicationContext context, Class<? super T> superClass,
			Class<?>... genericTypes) {
		return (T) context.getBeansOfType(superClass).values().stream().filter(
				e -> Arrays.equals(genericTypes, GenericTypeResolver.resolveTypeArguments(e.getClass(), superClass)))
				.findFirst().get();
	}

}
