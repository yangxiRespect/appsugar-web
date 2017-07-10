package org.appsugar.data.jpa.repository.querdsl;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.entity.LongIdEntity;
import org.appsugar.data.common.repository.querydsl.LongIdEntityQueryDslSpecification;

import com.querydsl.core.types.Path;

/**
 * JPA querydsl查询器实现
 * @author NewYoung
 * 2016年11月23日下午6:20:37
 */
public class JpaQueryDslSpecification<C extends LongIdEntityCondition, Q extends Path<? extends LongIdEntity>>
		extends LongIdEntityQueryDslSpecification<C, Q> {

}
