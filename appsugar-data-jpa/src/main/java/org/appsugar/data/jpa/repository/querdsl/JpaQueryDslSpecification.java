package org.appsugar.data.jpa.repository.querdsl;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.entity.LongIdEntity;

import com.querydsl.core.types.EntityPath;

/**
 * JPA querydsl查询器实现
 * @author NewYoung
 * 2016年11月23日下午6:20:37
 */
public class JpaQueryDslSpecification<C extends LongIdEntityCondition, Q extends EntityPath<? extends LongIdEntity>>
		extends JpaGenericQueryDslSpecification<Long, C, Q> {

}
