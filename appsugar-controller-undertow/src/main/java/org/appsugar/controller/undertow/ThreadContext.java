package org.appsugar.controller.undertow;

import io.undertow.server.HttpServerExchange;

/**
 * 线程上下文绑定
 * @author NewYoung
 *
 */
public class ThreadContext {
	private static ThreadLocal<HttpServerExchange> exchange = new ThreadLocal<>();

	public static HttpServerExchange getExchange() {
		return exchange.get();
	}

	public static void setExchange(HttpServerExchange e) {
		exchange.set(e);
	}

	public static void removeExchange() {
		exchange.remove();
	}
}
