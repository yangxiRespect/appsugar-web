package org.appsugar.controller.undertow;

import java.io.InputStream;
import java.util.Collection;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;

import javax.inject.Inject;

import org.appsugar.controller.common.ParameterResolvable;

import io.undertow.server.HttpServerExchange;

public class UndertowParameterResolvable implements ParameterResolvable {

	@Inject
	public UndertowParameterResolvable() {
		super();
	}

	@Override
	public String pathValue(String name) {
		Deque<String> q = pathParameters(name);
		return (Objects.isNull(q) || q.isEmpty()) ? null : q.getFirst();
	}

	@Override
	public String pathValue(String name, String defaultValue) {
		Deque<String> q = pathParameters(name);
		return (Objects.isNull(q) || q.isEmpty()) ? defaultValue : q.getFirst();
	}

	@Override
	public Collection<String> pathValues(String name) {
		return pathValues(name);
	}

	@Override
	public Collection<String> pathValues(String name, Collection<String> defaultValues) {
		Collection<String> result = pathValues(name);
		return (Objects.isNull(result) || result.isEmpty()) ? defaultValues : result;
	}

	@Override
	public String queryValue(String name) {
		Deque<String> q = queryParameters(name);
		return (Objects.isNull(q) || q.isEmpty()) ? null : q.getFirst();
	}

	@Override
	public String queryValue(String name, String defaultValue) {
		Deque<String> q = queryParameters(name);
		return (Objects.isNull(q) || q.isEmpty()) ? defaultValue : q.getFirst();
	}

	@Override
	public Collection<String> queryValues(String name) {
		return queryParameters(name);
	}

	@Override
	public Collection<String> queryValues(String name, Collection<String> defaultValues) {
		Collection<String> result = queryParameters(name);
		return (Objects.isNull(result) || result.isEmpty()) ? defaultValues : result;
	}

	protected Deque<String> pathParameters(String name) {
		return getAndValidHttpExhcange().getPathParameters().get(name);
	}

	protected Deque<String> queryParameters(String name) {
		return getAndValidHttpExhcange().getQueryParameters().get(name);
	}

	protected HttpServerExchange getAndValidHttpExhcange() {
		HttpServerExchange result = getHttpExchange();
		if (Objects.isNull(result)) {
			throw new RuntimeException("HttpServerExchange not bind to context");
		}
		return result;
	}

	protected HttpServerExchange getHttpExchange() {
		return ThreadContext.getExchange();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Collection<String>> pathParameter() {
		Map<String, ?> result = getAndValidHttpExhcange().getPathParameters();
		return (Map<String, Collection<String>>) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Collection<String>> queryParameter() {
		Map<String, ?> result = getAndValidHttpExhcange().getQueryParameters();
		return (Map<String, Collection<String>>) result;
	}

	@Override
	public InputStream getRequestInputStream() {
		return getAndValidHttpExhcange().getInputStream();
	}

}
