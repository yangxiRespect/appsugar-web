package org.appsugar.data.mongo.repository.querdsl;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.appsugar.bean.entity.StringIdEntity;
import org.appsugar.data.common.repository.querydsl.StringIdEntityQueryDslSpecification;

import com.querydsl.core.types.Path;

/**
 *mongo querydsl查询器 
 * @author NewYoung
 * 2016年11月23日下午6:22:39
 */
public class MongoQueryDslSpecification<C extends StringIdEntityCondition, Q extends Path<? extends StringIdEntity>>
		extends StringIdEntityQueryDslSpecification<C, Q> {

}
