package org.appsugar.controller.common;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

/**
 * 参数解析器
 * @author NewYoung
 *
 */
public interface ParameterResolvable {
	/**
	 * 根据名称获取uri对应值
	 */
	String pathValue(String name);

	/**
	 * @see this{@link #pathValue(String)}
	 */
	String pathValue(String name, String defaultValue);

	/**
	 * 根据名称获取uri对应值集合
	 */
	Collection<String> pathValues(String name);

	/**
	 * @see this{@link #pathValues(String)}
	 */
	Collection<String> pathValues(String name, Collection<String> defaultValues);

	/**
	 * 根据名称获取对应值
	 */
	String queryValue(String name);

	/**
	 * @see this{@link #queryValue(String)}
	 */
	String queryValue(String name, String defaultValue);

	/**
	 * 根据名称获取对应值集合
	 */
	Collection<String> queryValues(String name);

	/**
	 * @see this{@link #queryValues(String)}
	 */
	Collection<String> queryValues(String name, Collection<String> defaultValues);

	Map<String, Collection<String>> pathParameter();

	Map<String, Collection<String>> queryParameter();

	InputStream getRequestInputStream();
}
