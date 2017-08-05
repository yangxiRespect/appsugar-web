package org.appsugar.data.common.repository.querydsl;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.bean.entity.GenericIdEntity;
import org.appsugar.bean.entity.QGenericIdEntity;
import org.springframework.util.ReflectionUtils;

import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.Expressions;

/**
 * 类型安全查询指定器实现
 * @author NewYoung
 * 2016年11月23日下午2:33:11
 */
public class GenericEntityQueryDslSpecification<C extends GenericIdEntityCondition<? extends Serializable>, Q extends Path<? extends GenericIdEntity<? extends Serializable>>>
		implements QueryDslSpecification<C, Q> {

	protected DateTimePath<java.util.Date> createdAt;

	protected DateTimePath<java.util.Date> updatedAt;

	@Override
	public final Predicate toPredicate(C condition, Q root) {
		processPath(root);
		PredicateCollection collection = new PredicateCollection();
		toPredicate(collection, condition, root);
		if (Objects.equals(collection.size(), 0)) {
			return null;
		}
		if (Objects.equals(collection.size(), 1)) {
			return collection.getPredicateList().get(0);
		}
		return Expressions.allOf(collection.toArray());
	}

	/**
	 * 根据condition中的条件创建筛选器
	 * @author NewYoung
	 * 2016年11月23日下午2:44:48
	 */
	public void toPredicate(PredicateCollection pc, C condition, @SuppressWarnings("unused") Q root) {
		Date startAt = condition.getStartAt();
		if (Objects.nonNull(startAt)) {
			pc.add(createdAt.goe(startAt));
		}
		Date endAt = condition.getEndAt();
		if (Objects.nonNull(endAt)) {
			pc.add(createdAt.lt(endAt));
		}
		Date modifyStartAt = condition.getModifyStartAt();
		if (Objects.nonNull(modifyStartAt)) {
			pc.add(updatedAt.goe(modifyStartAt));
		}
		Date modifyEndAt = condition.getModifyEndAt();
		if (Objects.nonNull(modifyEndAt)) {
			pc.add(updatedAt.lt(modifyEndAt));
		}
	}

	protected void processPath(Q root) {
		QGenericIdEntity r = QGenericIdEntity.genericIdEntity;
		if (Objects.isNull(createdAt)) {
			createdAt = findPath(root, r.createdAt.getMetadata().getName());
		}
		if (Objects.isNull(updatedAt)) {
			updatedAt = findPath(root, r.updatedAt.getMetadata().getName());
		}
	}

	@SuppressWarnings("unchecked")
	protected <T> T findPath(Q root, String name) {
		return (T) ReflectionUtils.getField(ReflectionUtils.findField(root.getClass(), name), root);
	}
}
