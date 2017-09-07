package org.appsugar.controller.undertow.register;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.gson.Gson;

@Singleton
public class GsonHandlerRegister extends JsonHandlerRegister {

	Gson gson = new Gson();

	@Inject
	public GsonHandlerRegister() {
		super();
	}

	@Override
	protected byte[] resolveResult(Object result) {
		return gson.toJson(result).getBytes();
	}

}
