package org.appsugar.data.jpa.repository;

import java.io.Serializable;

import org.appsugar.bean.condition.StringIdEntityCondition;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * string类型id
 * @author NewYoung
 * 2016年1月28日下午5:58:56
 */
@NoRepositoryBean
public interface JpaStringIdEntityRepository<T extends Serializable, C extends StringIdEntityCondition>
		extends JpaGenericIdEntityRepository<T, String, C> {

}
