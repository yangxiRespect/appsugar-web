package org.appsugar.data.jpa.repository;

import java.io.Serializable;

import org.appsugar.bean.condition.GenericIdEntityCondition;
import org.appsugar.data.common.repository.GenericIdEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author NewYoung
 * 2016年1月28日下午5:58:56
 */
@NoRepositoryBean
public interface JpaGenericIdEntityRepository<T extends Serializable, ID extends Serializable, C extends GenericIdEntityCondition<ID>>
		extends JpaRepository<T, ID>, GenericIdEntityRepository<ID, T, C>, JpaSpecificationExecutor<T>,
		QueryDslPredicateExecutor<T> {

}
