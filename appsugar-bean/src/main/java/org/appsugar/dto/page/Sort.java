package org.appsugar.dto.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sort implements Serializable {
	private static final long serialVersionUID = 3328391898674977140L;
	public static final Direction ASC_DIRECTION = Direction.ASC;
	public static final Direction DESC_DIRECTION = Direction.DESC;

	/**
	 * 排序字段集合
	 */
	private List<Order> orderList = new ArrayList<>(1);

	public Sort(String... properties) {
		this(ASC_DIRECTION, properties);
	}

	public Sort(Direction direction, String... properties) {
		for (String propertity : properties) {
			orderList.add(new Order(propertity, direction));
		}
	}

	public Sort(Order... orders) {
		orderList.addAll(Arrays.asList(orders));
	}

	public List<Order> getOrderList() {
		return orderList;
	}

	public static enum Direction {
		ASC, DESC
	}
}
