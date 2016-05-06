package org.appsugar.repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.appsugar.dto.page.Page;
import org.appsugar.dto.page.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class PageUtils {

	public static org.springframework.data.domain.Pageable toPageable(Pageable pageable) {
		return new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), toSort(pageable.getSort()));
	}

	public static Sort toSort(org.appsugar.dto.page.Sort sort) {
		if (sort == null) {
			return null;
		}
		List<Order> orderList = new ArrayList<>(sort.getOrderList().size());
		for (org.appsugar.dto.page.Order order : sort.getOrderList()) {
			Direction direction = order.getDirection() == org.appsugar.dto.page.Sort.ASC_DIRECTION ? Direction.ASC
					: Direction.DESC;
			orderList.add(new Order(direction, order.getPropertity()));
		}
		return new Sort(orderList);
	}

	public static <T extends Serializable> Page<T> toPage(org.springframework.data.domain.Page<T> page,
			Pageable pageable) {
		return new Page<T>(page.getContent(), pageable, (int) page.getTotalElements());
	}
}
