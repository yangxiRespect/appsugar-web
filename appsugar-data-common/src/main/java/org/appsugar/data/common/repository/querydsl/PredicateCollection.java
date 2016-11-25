package org.appsugar.data.common.repository.querydsl;

import java.util.List;

import com.google.common.collect.Lists;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

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

	public Predicate allOf() {
		if (predicateList.isEmpty()) {
			return null;
		}
		if (predicateList.size() == 1) {
			return predicateList.get(0);
		}
		return Expressions.allOf(toArray());
	}

	public int size() {
		return predicateList.size();
	}
}
