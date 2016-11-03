package org.appsugar.data.jpa.repository;

import java.util.List;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.entity.LongIdEntity;
import org.appsugar.data.common.repository.GenericIdEntityRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author NewYoung
 * 2016年1月28日下午5:58:56
 */
@NoRepositoryBean
public interface JpaIdEntityRepository<T extends LongIdEntity, C extends LongIdEntityCondition>
		extends JpaRepository<T, Long>, GenericIdEntityRepository<Long, T>, JpaSpecificationExecutor<T> {

	/** 
	 * 根据条件分页查询
	 */
	public Page<T> findPageByCondition(C condition, Pageable pageable);

	/**
	 * 根据条件查询所以
	 */
	public List<T> findByCondition(C condition);
}
