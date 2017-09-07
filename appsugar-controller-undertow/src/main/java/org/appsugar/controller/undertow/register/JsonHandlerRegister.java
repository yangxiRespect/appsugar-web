package org.appsugar.controller.undertow.register;

import io.undertow.server.HttpServerExchange;
import io.undertow.util.HeaderMap;
import io.undertow.util.Headers;

public abstract class JsonHandlerRegister extends AbstractHandlerRegister {

	@Override
	protected void preprocess(HttpServerExchange exchange) {
		HeaderMap map = exchange.getResponseHeaders();
		map.put(Headers.CONTENT_TYPE, "application/json;charset=UTF-8");
	}

}
