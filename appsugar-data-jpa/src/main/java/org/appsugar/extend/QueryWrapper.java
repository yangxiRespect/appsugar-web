package org.appsugar.extend;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;

import com.google.common.collect.Lists;

/**
 * 查询包装器
 * @author NewYoung
 * 2016年2月18日下午2:24:21
 */
public class QueryWrapper {

	protected CriteriaQuery<?> query;

	protected CriteriaBuilder criteriaBuilder;

	protected List<Predicate> predicateList = Lists.newArrayList();

	public QueryWrapper(CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		super();
		this.query = query;
		this.criteriaBuilder = criteriaBuilder;
	}

	/**
	 * 添加一个条件查询器
	 */
	public void add(Predicate predicate) {
		predicateList.add(predicate);
	}

	/**
	 * 把所有查询语句用and链接
	 */
	public Predicate toPredicate() {
		return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
	}

	/**
	 * 把条件应用到查询上
	 */
	public void applyPredicate() {
		query.where(toPredicate());
	}

	public CriteriaQuery<?> getQuery() {
		return query;
	}

	public CriteriaBuilder getCriteriaBuilder() {
		return criteriaBuilder;
	}

}
