package org.appsugar.controller.vertx;

import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

import org.appsugar.controller.common.BaseController;
import org.appsugar.controller.common.ParameterResolvable;
import org.appsugar.controller.common.ServerConfigModule.Address;
import org.appsugar.controller.vertx.register.JacksonHandlerRegister;
import org.appsugar.controller.vertx.register.JsonHandlerRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import dagger.Module;
import dagger.Provides;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

@Module
public class VertxModule {
	private static final Logger logger = LoggerFactory.getLogger(VertxModule.class);

	Vertx vertx;

	ObjectMapper objectMapper;

	VertxModule(Vertx vertx, ObjectMapper objectMapper) {
		super();
		this.vertx = vertx;
		this.objectMapper = objectMapper;
	}

	public VertxModule() {
		super();
	}

	@Provides
	@Singleton
	public Router router(Vertx v) {
		return Router.router(v);
	}

	@Provides
	@Singleton
	public Vertx vertx() {
		return this.vertx != null ? vertx : Vertx.vertx();
	}

	@Provides
	@Singleton
	public HttpServer server(Vertx v, Router handler, Set<BaseController> controllers, List<Address> address) {
		for (BaseController c : controllers) {
			logger.info("prepar to register handler for {}", c.getClass());
			c.register();
		}
		long time = System.currentTimeMillis();
		HttpServer server = v.createHttpServer();
		address.stream().forEach(e -> server.requestHandler(handler::accept).listen(e.port, e.host));
		logger.info("server listening on {}  spend {} ", address, System.currentTimeMillis() - time);
		return server;
	}

	@Provides
	@Singleton
	public ParameterResolvable parameterResolvable() {
		return new VertxParameterResolvable();
	}

	@Provides
	@Singleton
	public ObjectMapper objectMapper() {
		return objectMapper == null ? new ObjectMapper() : objectMapper;
	}

	@Provides
	@Singleton
	public JsonHandlerRegister jsonHandlerRegister(JacksonHandlerRegister register) {
		return register;
	}

	public static class Builder {
		Vertx v;
		ObjectMapper om;

		public Builder vertx(Vertx v) {
			this.v = v;
			return this;
		}

		public Builder objectMapper(ObjectMapper om) {
			this.om = om;
			return this;
		}

		public VertxModule build() {
			return new VertxModule(v, om);
		}

		public static Builder create() {
			return new Builder();
		}
	}
}
