package org.appsugar.data.jpa.repository.querdsl;

import java.io.Serializable;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.bean.entity.GenericIdEntity;
import org.appsugar.data.common.repository.querydsl.GenericEntityQueryDslSpecification;

import com.querydsl.core.types.EntityPath;

/**
 * JPA querydsl查询器实现
 * @author NewYoung
 * 2016年11月23日下午6:20:37
 */
public class JpaGenericQueryDslSpecification<ID extends Serializable, C extends GenericIdEntityCondition<ID>, Q extends EntityPath<? extends GenericIdEntity<ID>>>
		extends GenericEntityQueryDslSpecification<C, Q> {

}
