package org.appsugar.dto.page;

import java.io.Serializable;

import org.appsugar.dto.page.Sort.Direction;

public class Order implements Serializable {

	private static final long serialVersionUID = 7344337303196936185L;
	/**
	 * 排序知道
	 */
	private String propertity;
	/**
	 * 升序or降序
	 */
	private Direction direction;

	public Order() {
		super();
	}

	public Order(String propertity, Direction direction) {
		super();
		this.propertity = propertity;
		this.direction = direction;
	}

	public String getPropertity() {
		return propertity;
	}

	public void setPropertity(String propertity) {
		this.propertity = propertity;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [propertity=").append(propertity).append(", direction=").append(direction).append("]");
		return builder.toString();
	}

}
