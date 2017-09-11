package org.appsugar.controller.vertx;

import io.vertx.ext.web.RoutingContext;

/**
 * 请求上下文
 * @author NewYoung
 *
 */
public class ThreadContext {
	private static ThreadLocal<RoutingContext> request = new ThreadLocal<>();

	public static RoutingContext getRequest() {
		return request.get();
	}

	public static void setRequest(RoutingContext e) {
		request.set(e);
	}

	public static void removeRequest() {
		request.remove();
	}
}
