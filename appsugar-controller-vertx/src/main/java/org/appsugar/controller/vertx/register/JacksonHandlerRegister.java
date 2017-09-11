package org.appsugar.controller.vertx.register;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Singleton
public class JacksonHandlerRegister extends JsonHandlerRegister {
	@Inject
	ObjectMapper mapper;

	@Inject
	public JacksonHandlerRegister() {
		super();
	}

	@Override
	protected byte[] resolveResult(Object result) {
		try {
			return mapper.writeValueAsBytes(result);
		} catch (JsonProcessingException e) {
			logger.error("write json error ", e);
			throw new RuntimeException(e);
		}
	}

}
