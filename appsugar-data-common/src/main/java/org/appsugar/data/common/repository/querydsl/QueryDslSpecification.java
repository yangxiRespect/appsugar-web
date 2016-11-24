package org.appsugar.data.common.repository.querydsl;

import java.io.Serializable;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.bean.entity.GenericIdEntity;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Predicate;

/**
 * 类型安全查询器
 * @author NewYoung
 * 2016年11月23日下午2:02:06
 */
public interface QueryDslSpecification<C extends GenericIdEntityCondition<? extends Serializable>, Q extends EntityPath<? extends GenericIdEntity<? extends Serializable>>> {

	/**
	 * 根据condition转换Predicate
	 * @author NewYoung
	 * 2016年11月23日下午2:06:11
	 */
	public Predicate toPredicate(C condition, Q root);

}
