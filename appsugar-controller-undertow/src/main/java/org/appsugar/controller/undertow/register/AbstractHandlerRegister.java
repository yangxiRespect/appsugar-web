package org.appsugar.controller.undertow.register;

import java.nio.ByteBuffer;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

import javax.inject.Inject;

import org.appsugar.controller.common.ExceptionHandler;
import org.appsugar.controller.common.HandlerRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.undertow.server.HttpServerExchange;
import io.undertow.server.RoutingHandler;

/**
 * 基于fastJson序列化与解析的处理器注册
 * @author NewYoung
 *
 */
public abstract class AbstractHandlerRegister implements HandlerRegister {

	private static final Logger logger = LoggerFactory.getLogger(AbstractHandlerRegister.class);

	@Inject
	RoutingHandler routingHandler;

	@Inject
	protected ExceptionHandler exceptionHandler;

	@Override
	public HandlerRegister method(String method, String template, Supplier<Object> supplier) {
		register(method, template, supplier);
		return this;
	}

	@SuppressWarnings("unchecked")
	void register(String method, String template, Supplier<Object> function) {
		logger.debug("prepar  register {} to {}", template, method);
		routingHandler.add(method, template, exchange -> {
			try {
				Object result = function.get();
				if (result instanceof CompletableFuture) {
					((CompletableFuture<Object>) result).whenComplete((r, e) -> {
						if (Objects.isNull(e)) {
							response(exchange, r);
						} else {
							processException(exchange, e);
						}
					});
					return;
				}
				response(exchange, result);
			} catch (Throwable ex) {
				processException(exchange, ex);
			}
		});
	}

	protected void processException(HttpServerExchange exchange, Throwable t) {
		if (Objects.isNull(exceptionHandler)) {
			return;
		}
		Object result = exceptionHandler.handle(t);
		if (Objects.isNull(result)) {
			logger.error("process request cause exception  uri {}", exchange.getRequestPath(), t);
			return;
		}
		response(exchange, result);
	}

	protected void response(HttpServerExchange exchange, Object object) {
		byte[] content = resolveResult(object);
		preprocess(exchange);
		exchange.getResponseSender().send(ByteBuffer.wrap(content));
		exchange.endExchange();
	}

	protected abstract byte[] resolveResult(Object result);

	protected abstract void preprocess(HttpServerExchange exchange);
}
