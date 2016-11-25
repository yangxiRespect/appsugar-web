package org.appsugar.data.common.repository.querydsl;

import java.util.List;

import com.google.common.collect.Lists;
import com.querydsl.core.types.dsl.BooleanExpression;

/**
 * 条件集合
 * @author NewYoung
 * 2016年11月24日下午1:00:59
 */
public class PredicateCollection {

	private List<BooleanExpression> predicateList = Lists.newArrayList();

	/**
	 * 添加条件
	 * @author NewYoung
	 * 2016年11月24日下午1:02:16
	 */
	public void add(BooleanExpression predicate) {
		predicateList.add(predicate);
	}

	public List<BooleanExpression> getPredicateList() {
		return predicateList;
	}

	public BooleanExpression[] toArray() {
		return predicateList.toArray(new BooleanExpression[predicateList.size()]);
	}

	public int size() {
		return predicateList.size();
	}
}
