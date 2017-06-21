package org.appsugar.data.jpa.repository.querdsl;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.entity.StringIdEntity;

import com.querydsl.core.types.EntityPath;

/**
 * JPA querydsl查询器实现
 * @author NewYoung
 * 2016年11月23日下午6:20:37
 */
public class JpaStringQueryDslSpecification<C extends StringIdEntityCondition, Q extends EntityPath<? extends StringIdEntity>>
		extends JpaGenericQueryDslSpecification<String, C, Q> {

}
