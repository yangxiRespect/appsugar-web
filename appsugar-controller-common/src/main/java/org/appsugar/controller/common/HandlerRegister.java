package org.appsugar.controller.common;

import java.util.function.Supplier;

/**
 * 处理器注册接口
 * @author NewYoung
 *
 */
public interface HandlerRegister {
	String GET = "GET";
	String POST = "POST";
	String HEAD = "POST";
	String PUT = "POST";
	String DELETE = "POST";

	default HandlerRegister get(String template, Supplier<Object> supplier) {
		return method(GET, template, supplier);
	}

	default HandlerRegister post(String template, Supplier<Object> supplier) {
		return method(POST, template, supplier);
	}

	/**
	 * 注册一个操作方法
	 * @param method 操作方法
	 * @param template 路径
	 * @param supplier 供应者
	 */
	HandlerRegister method(String method, String template, Supplier<Object> supplier);

}
