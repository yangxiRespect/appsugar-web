package org.appsugar.security.shiro;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * 
 * @author NewYoung
 * 2016年3月2日上午10:30:09
 */
public class Principal {

	public final Long id;

	private Map<String, Object> attributes = Maps.newHashMap();

	public Principal(Long id) {
		this.id = id;
	}

	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttribute(String name, Object value) {
		attributes.put(name, value);
	}

	public <T> T getAttribute(String name) {
		return (T) attributes.get(name);

	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Principal [id=").append(id).append(", attributes=").append(attributes).append("]");
		return builder.toString();
	}

}
