package org.appsugar.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.appsugar.condition.IdEntityCondition;
import org.appsugar.domain.Page;
import org.appsugar.domain.Pageable;
import org.appsugar.entity.IdEntity;
import org.appsugar.specification.IdEntitySpecification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 针对idEntity bean做增强
 * @author NewYoung
 * 2016年2月25日下午2:33:11
 */
public class IdEntityRepositoryImpl<T extends IdEntity, C extends IdEntityCondition>
		extends SimpleJpaRepository<T, Long> implements IdEntityRepository<T, C> {

	protected IdEntitySpecification<T, C> specification;

	public IdEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	@Override
	public <S extends T> S save(S entity) {
		beforeSave(entity);
		return super.save(entity);
	}

	protected void beforeSave(IdEntity entity) {
		Date date = new Date();
		if (entity.getId() == null) {
			entity.setCreatedAt(date);
		}
		entity.setUpdatedAt(date);
	}

	@Override
	public Page<T> findAll(C condition, Pageable pageable) {
		return PageUtils.toPage(findAll(specification.clone(condition), PageUtils.toPageable(pageable)), pageable);
	}

	@Override
	public List<T> findAll(C condition) {
		return findAll(specification.clone(condition));
	}

}
