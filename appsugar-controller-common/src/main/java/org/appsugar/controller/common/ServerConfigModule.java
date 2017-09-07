package org.appsugar.controller.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.ElementsIntoSet;

/**
 * 服务配置module
 * @author NewYoung
 *
 */
@Module
public class ServerConfigModule {

	List<Address> address = Arrays.asList(new Address(8080, "0.0.0.0"));

	public ServerConfigModule() {
		super();
	}

	ServerConfigModule(List<Address> address) {
		super();
		this.address = address;
	}

	@Provides
	@Singleton
	public List<Address> address() {
		return Collections.unmodifiableList(address);
	}

	@Provides
	@Singleton
	@ElementsIntoSet
	public Set<BaseController> controllers() {
		return new HashSet<>();
	}

	public static class Address {
		public final int port;
		public final String host;

		public Address(int port, String host) {
			super();
			this.port = port;
			this.host = host;
		}

		@Override
		public String toString() {
			return "Address [port=" + port + ", host=" + host + "]";
		}
	}

	public static class Builder {
		List<Address> address;

		public Builder() {
			address = new ArrayList<>();
		}

		public Builder address(Address add) {
			address.add(add);
			return this;
		}

		public ServerConfigModule build() {
			return new ServerConfigModule(this.address);
		}

		public static Builder create() {
			return new Builder();
		}
	}
}
