package org.appsugar.controller.vertx.register;

import io.vertx.core.http.HttpServerResponse;

public abstract class JsonHandlerRegister extends AbstractHandlerRegister {

	@Override
	protected void preprocess(HttpServerResponse res) {
		res.putHeader("Content-Type", "application/json;charset=UTF-8");
	}

}
