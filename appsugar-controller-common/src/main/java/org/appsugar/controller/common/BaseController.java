package org.appsugar.controller.common;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import javax.inject.Inject;

/**
 * 控制器父类
 * @author NewYoung
 *
 */
public class BaseController implements ParameterResolvable {

	@Inject
	ParameterResolvable parameterResolvable;

	@Override
	public String pathValue(String name) {
		return parameterResolvable.pathValue(name);
	}

	@Override
	public String pathValue(String name, String defaultValue) {
		return parameterResolvable.pathValue(name, defaultValue);
	}

	@Override
	public Collection<String> pathValues(String name) {
		return parameterResolvable.pathValues(name);
	}

	@Override
	public Collection<String> pathValues(String name, Collection<String> defaultValues) {
		return parameterResolvable.pathValues(name, defaultValues);
	}

	@Override
	public String queryValue(String name) {
		return parameterResolvable.queryValue(name);
	}

	@Override
	public String queryValue(String name, String defaultValue) {
		return parameterResolvable.queryValue(name, defaultValue);
	}

	@Override
	public Collection<String> queryValues(String name) {
		return parameterResolvable.queryValues(name);
	}

	@Override
	public Collection<String> queryValues(String name, Collection<String> defaultValues) {
		return parameterResolvable.queryValues(name, defaultValues);
	}

	@Override
	public Map<String, Collection<String>> pathParameter() {
		return parameterResolvable.pathParameter();
	}

	@Override
	public Map<String, Collection<String>> queryParameter() {
		return parameterResolvable.queryParameter();
	}

	public void register() {

	}

	@Override
	public InputStream getRequestInputStream() {
		return parameterResolvable.getRequestInputStream();
	}

}
