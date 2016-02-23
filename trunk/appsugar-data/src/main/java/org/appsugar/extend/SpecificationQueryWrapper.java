package org.appsugar.extend;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * 针对spring jpa data扩展
 * @author NewYoung
 * 2016年2月18日下午2:56:01
 * @param <T>
 */
public class SpecificationQueryWrapper<T> extends QueryWrapper<T> {

	private Root<T> root;

	public SpecificationQueryWrapper(CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, Root<T> root) {
		super(query, criteriaBuilder);
		this.root = root;
	}

	public Root<T> getRoot() {
		return root;
	}

}
