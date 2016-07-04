package org.appsugar.data.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.entity.LongIdEntity;
import org.appsugar.data.common.util.PageUtils;
import org.appsugar.specification.IdEntitySpecification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 针对idEntity bean做增强
 * @author NewYoung
 * 2016年2月25日下午2:33:11
 */
public class IdEntityRepositoryImpl<T extends LongIdEntity, C extends LongIdEntityCondition>
		extends SimpleJpaRepository<T, Long> implements IdEntityRepository<T, C> {

	protected IdEntitySpecification<T, C> specification;

	public IdEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	@Override
	public Page<T> findAll(C condition, Pageable pageable) {
		try {
			return PageUtils.toPage(findAll(specification.clone(condition), PageUtils.toPageable(pageable)), pageable);
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<T> findAll(C condition) {
		try {
			return findAll(specification.clone(condition));
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

}
