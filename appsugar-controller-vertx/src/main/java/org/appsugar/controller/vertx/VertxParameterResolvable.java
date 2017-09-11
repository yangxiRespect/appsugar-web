package org.appsugar.controller.vertx;

import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.appsugar.controller.common.ParameterResolvable;

import io.netty.buffer.ByteBufInputStream;
import io.vertx.core.MultiMap;
import io.vertx.core.buffer.Buffer;
import io.vertx.ext.web.RoutingContext;

@Singleton
public class VertxParameterResolvable implements ParameterResolvable {

	@Inject
	public VertxParameterResolvable() {
		super();
	}

	@Override
	public String pathValue(String name) {
		return pathValue(name, null);
	}

	@Override
	public String pathValue(String name, String defaultValue) {
		Collection<String> values = params(name);
		if (Objects.isNull(values)) {
			return defaultValue;
		}
		return ((List<String>) values).get(0);
	}

	@Override
	public Collection<String> pathValues(String name) {
		return pathValues(name, null);
	}

	@Override
	public Collection<String> pathValues(String name, Collection<String> defaultValues) {
		Collection<String> values = params(name);
		return values == null ? defaultValues : values;
	}

	@Override
	public String queryValue(String name) {
		return queryValue(name, null);
	}

	@Override
	public String queryValue(String name, String defaultValue) {
		Collection<String> values = params(name);
		if (Objects.isNull(values)) {
			return defaultValue;
		}
		return ((List<String>) values).get(0);
	}

	@Override
	public Collection<String> queryValues(String name) {
		return queryValues(name, Collections.emptyList());
	}

	@Override
	public Collection<String> queryValues(String name, Collection<String> defaultValues) {
		Collection<String> result = params(name);
		return result == null ? defaultValues : result;
	}

	Collection<String> params(String key) {
		return queryParameter().get(key);
	}

	@Override
	public Map<String, Collection<String>> pathParameter() {
		return queryParameter();
	}

	@Override
	public Map<String, Collection<String>> queryParameter() {
		RoutingContext ctx = getAndValidRoutingContext();
		String attrKey = "map_key";
		Map<String, Collection<String>> result = ctx.get(attrKey);
		if (Objects.nonNull(result)) {
			return result;
		}
		result = new HashMap<>();
		ctx.put(attrKey, result);
		MultiMap map = ctx.request().params();
		for (String key : map.names()) {
			result.put(key, map.getAll(key));
		}
		return result;
	}

	@Override
	public InputStream getRequestInputStream() {
		RoutingContext ctx = getAndValidRoutingContext();
		Buffer body = ctx.getBody();
		return new ByteBufInputStream(body.getByteBuf());
	}

	protected RoutingContext getAndValidRoutingContext() {
		RoutingContext result = getHttpRoutingContext();
		if (Objects.isNull(result)) {
			throw new RuntimeException("HttpServerExchange not bind to context");
		}
		return result;
	}

	protected RoutingContext getHttpRoutingContext() {
		return ThreadContext.getRequest();
	}
}
