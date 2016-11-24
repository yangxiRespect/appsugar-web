package org.appsugar.data.common.repository.querydsl;

import java.util.List;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;

/**
 * 条件集合
 * @author NewYoung
 * 2016年11月24日下午1:00:59
 */
public class PredicateCollection {

	private List<Predicate> predicateList = Lists.newArrayList();

	/**
	 * 添加条件
	 * @author NewYoung
	 * 2016年11月24日下午1:02:16
	 */
	public void add(Predicate predicate) {
		predicateList.add(predicate);
	}

	public List<Predicate> getPredicateList() {
		return predicateList;
	}

	public Predicate[] toArray() {
		return predicateList.toArray(new Predicate[predicateList.size()]);
	}

	public int size() {
		return predicateList.size();
	}
}
