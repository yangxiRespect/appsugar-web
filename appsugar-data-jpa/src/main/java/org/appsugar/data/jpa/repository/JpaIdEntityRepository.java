package org.appsugar.data.jpa.repository;

import java.io.Serializable;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * long类型id
 * @author NewYoung
 * 2016年1月28日下午5:58:56
 */
@NoRepositoryBean
public interface JpaIdEntityRepository<T extends Serializable, C extends LongIdEntityCondition>
		extends JpaGenericIdEntityRepository<T, Long, C> {

}
