package org.appsugar.data.common.repository.querydsl;

import java.util.Objects;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.entity.QStringIdEntity;
import org.appsugar.bean.entity.StringIdEntity;

import com.google.common.base.Strings;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.StringPath;

public class StringIdEntityQueryDslSpecification<C extends StringIdEntityCondition, Q extends EntityPath<? extends StringIdEntity>>
		extends GenericEntityQueryDslSpecification<C, Q> {

	private StringPath id;

	@Override
	public void toPredicate(PredicateCollection pc, C condition, Q root) {
		super.toPredicate(pc, condition, root);
		String idValue = condition.getId();
		if (!Strings.isNullOrEmpty(idValue)) {
			pc.add(id.eq(idValue));
		}
	}

	@Override
	protected void processPath(Q root) {
		super.processPath(root);
		if (Objects.isNull(id)) {
			id = findPath(root, QStringIdEntity.stringIdEntity.id.getMetadata().getName());
		}
	}

}
