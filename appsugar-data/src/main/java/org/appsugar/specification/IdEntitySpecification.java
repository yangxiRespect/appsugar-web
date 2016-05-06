package org.appsugar.specification;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.appsugar.condition.IdEntityCondition;
import org.appsugar.entity.IdEntity;
import org.appsugar.extend.SpecificationQueryWrapper;
import org.springframework.data.jpa.domain.Specification;

/**
 * 条件查询基类
 * id eq
 * createdStart  greaterThanEqulas
 * createdEnd    lessThan
 * @author NewYoung
 * 2016年2月18日下午2:58:37
 * @param <T>
 * @param <C>
 */
public class IdEntitySpecification<T extends IdEntity, C extends IdEntityCondition>
		implements Specification<T>, Cloneable {

	private C conditionObject;

	public IdEntitySpecification(C conditionObject) {
		super();
		this.conditionObject = conditionObject;
	}

	public IdEntitySpecification() {
		super();
	}

	@Override
	public final Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		SpecificationQueryWrapper<T> spe = new SpecificationQueryWrapper<>(query, cb, root);
		addCondition(spe, root, cb, conditionObject);
		return spe.toPredicate();
	}

	protected void addCondition(SpecificationQueryWrapper<T> query, Root<T> root, CriteriaBuilder cb, C condition) {
		Long id = condition.getId();
		if (id != null) {
			query.add(cb.equal(root.get(IdEntity._id), id));
		}
		Date createdStart = condition.getCreatedStart();
		Expression<Date> createdAtExpression = root.get(IdEntity._createdAt);
		if (createdStart != null) {
			query.add(cb.greaterThanOrEqualTo(createdAtExpression, createdStart));
		}
		Date createdEnd = condition.getCreatedEnd();
		if (createdEnd != null) {
			query.add(cb.lessThan(createdAtExpression, createdEnd));
		}
	}

	public IdEntitySpecification<T, C> clone(C condition) {
		try {
			@SuppressWarnings("unchecked")
			IdEntitySpecification<T, C> copy = (IdEntitySpecification<T, C>) clone();
			copy.conditionObject = condition;
			return copy;
		} catch (CloneNotSupportedException e) {
			//ugly design
			return null;
		}
	}
}
