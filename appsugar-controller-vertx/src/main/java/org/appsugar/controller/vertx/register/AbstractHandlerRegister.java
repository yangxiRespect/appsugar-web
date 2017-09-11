package org.appsugar.controller.vertx.register;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import javax.inject.Inject;

import org.appsugar.controller.common.ExceptionHandler;
import org.appsugar.controller.common.HandlerRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public abstract class AbstractHandlerRegister implements HandlerRegister {

	Logger logger = LoggerFactory.getLogger(getClass());

	@Inject
	Router router;

	@Inject
	protected ExceptionHandler exceptionHandler;

	@Override
	public HandlerRegister method(String method, String template, Supplier<Object> supplier) {
		//TODO process template {xxx}  to  :xxx
		router.route(template).method(HttpMethod.valueOf(method)).handler(e -> handler(supplier, e));
		return this;
	}

	protected void handler(Supplier<Object> supplier, RoutingContext ctx) {
		HttpServerResponse response = ctx.response();
		try {
			Object result = supplier.get();
			if (result instanceof CompletableFuture) {
				((CompletableFuture<Object>) result).whenComplete((r, e) -> {
					if (Objects.isNull(e)) {
						response(response, r);
					} else {
						processException(ctx, e);
					}
				});
				return;
			}
			response(response, result);
		} catch (Throwable ex) {
			processException(ctx, ex);
		}
	}

	protected void processException(RoutingContext ctx, Throwable t) {
		if (Objects.isNull(exceptionHandler)) {
			return;
		}
		Object result = exceptionHandler.handle(t);
		if (Objects.isNull(result)) {
			logger.error("process request cause exception  uri {}", ctx.request().uri(), t);
			return;
		}
		response(ctx.response(), result);
	}

	protected void response(HttpServerResponse response, Object object) {
		byte[] content = resolveResult(object);
		preprocess(response);
		response.end(Buffer.buffer(content));

	}

	protected abstract byte[] resolveResult(Object result);

	protected abstract void preprocess(HttpServerResponse exchange);
}
