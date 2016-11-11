package org.appsugar.data.jpa.repository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
public class JpaIdEntityRepositoryImpl<T extends LongIdEntity, C extends LongIdEntityCondition>
		extends SimpleJpaRepository<T, Long> implements JpaIdEntityRepository<T, C> {

	protected IdEntitySpecification<T, C> specification;

	public JpaIdEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	@Override
	public Page<T> findPageByCondition(C condition, Pageable pageable) {
		try {
			return PageUtils.toPage(findAll(specification.clone(condition), PageUtils.toPageable(pageable)), pageable);
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<T> findByCondition(C condition) {
		try {
			return findAll(specification.clone(condition));
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <S extends T> S save(S entity) {
		Date date = new Date();
		if (Objects.isNull(entity.getId())) {
			entity.setCreatedAt(date);
		}
		entity.setUpdatedAt(date);
		return super.save(entity);
	}

	@Override
	public <S extends T> List<S> save(Iterable<S> entities) {
		return super.save(entities);
	}

}
