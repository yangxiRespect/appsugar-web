package org.appsugar.repository;

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
public interface IdEntityRepository<T extends IdEntity> extends JpaRepository<T, Long>, JpaSpecificationExecutor<T> {

}
