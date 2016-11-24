package org.appsugar.data.jpa.repository;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.entity.LongIdEntity;
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
public interface JpaIdEntityRepository<T extends LongIdEntity, C extends LongIdEntityCondition>
		extends JpaRepository<T, Long>, GenericIdEntityRepository<Long, T, C>, JpaSpecificationExecutor<T>,
		QueryDslPredicateExecutor<T> {

}
