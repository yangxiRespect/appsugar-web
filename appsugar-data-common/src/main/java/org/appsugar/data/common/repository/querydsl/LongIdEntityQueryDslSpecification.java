package org.appsugar.data.common.repository.querydsl;

import java.util.Objects;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.entity.LongIdEntity;
import org.appsugar.bean.entity.QLongIdEntity;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.NumberPath;

public class LongIdEntityQueryDslSpecification<C extends LongIdEntityCondition, Q extends EntityPath<? extends LongIdEntity>>
		extends GenericEntityQueryDslSpecification<C, Q> {

	protected NumberPath<Long> id;

	@Override
	public void toPredicate(PredicateCollection pc, C condition, Q root) {
		super.toPredicate(pc, condition, root);
		Long idValue = condition.getId();
		if (Objects.nonNull(idValue)) {
			pc.add(id.eq(idValue));
		}
	}

	@Override
	protected void processPath(Q root) {
		super.processPath(root);
		QLongIdEntity r = QLongIdEntity.longIdEntity;
		if (Objects.isNull(id)) {
			id = findPath(root, r.id.getMetadata().getName());
		}
	}
}
