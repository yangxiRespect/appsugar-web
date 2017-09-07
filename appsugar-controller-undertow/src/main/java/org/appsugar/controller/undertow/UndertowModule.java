package org.appsugar.controller.undertow;

import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

import org.appsugar.controller.common.BaseController;
import org.appsugar.controller.common.ParameterResolvable;
import org.appsugar.controller.common.ServerConfigModule.Address;
import org.appsugar.controller.undertow.register.GsonHandlerRegister;
import org.appsugar.controller.undertow.register.JsonHandlerRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dagger.Module;
import dagger.Provides;
import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.RoutingHandler;

@Module
public class UndertowModule {

	private static final Logger logger = LoggerFactory.getLogger(UndertowModule.class);

	@Provides
	@Singleton
	public RoutingHandler routingHandler() {
		return Handlers.routing();
	}

	@Provides
	@Singleton
	public Undertow undertow(RoutingHandler handler, Set<BaseController> controllers, List<Address> address) {
		for (BaseController c : controllers) {
			logger.info("prepar to register handler for {}", c.getClass());
			c.register();
		}
		long time = System.currentTimeMillis();
		Undertow.Builder builder = Undertow.builder().setWorkerThreads(1);
		address.forEach(e -> builder.addHttpListener(e.port, e.host));
		Undertow server = builder.setHandler(handler).build();
		server.start();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> server.stop()));
		logger.info("server listening on {}  spend {} ", address, System.currentTimeMillis() - time);
		return server;
	}

	@Provides
	@Singleton
	public ParameterResolvable parameterResolvable() {
		return new UndertowParameterResolvable();
	}

	@Provides
	@Singleton
	public JsonHandlerRegister jsonHandlerRegister(GsonHandlerRegister register) {
		return register;
	}

}
