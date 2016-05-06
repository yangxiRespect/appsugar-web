package org.appsugar.repository;

import java.util.List;

import org.appsugar.condition.IdEntityCondition;
import org.appsugar.dto.page.Page;
import org.appsugar.dto.page.Pageable;
import org.appsugar.entity.IdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * 
 * @author NewYoung
 * 2016年1月28日下午5:58:56
 */
@NoRepositoryBean
public interface IdEntityRepository<T extends IdEntity, C extends IdEntityCondition>
		extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

	/**
	 * 根据条件分页查询
	 */
	public Page<T> findAll(C condition, Pageable pageable);

	/**
	 * 根据条件查询所以
	 */
	public List<T> findAll(C condition);
}
