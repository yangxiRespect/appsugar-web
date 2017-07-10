package org.appsugar.data.jpa.repository.querdsl;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.entity.StringIdEntity;
import org.appsugar.data.common.repository.querydsl.StringIdEntityQueryDslSpecification;

import com.querydsl.core.types.Path;

/**
 * JPA querydsl查询器实现
 * @author NewYoung
 * 2016年11月23日下午6:20:37
 */
public class JpaStringQueryDslSpecification<C extends StringIdEntityCondition, Q extends Path<? extends StringIdEntity>>
		extends StringIdEntityQueryDslSpecification<C, Q> {

}
