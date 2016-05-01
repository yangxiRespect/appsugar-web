package org.appsugar.repository;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.EntityManager;

import org.appsugar.entity.IdEntity;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

/**
 * 针对idEntity bean做增强
 * @author NewYoung
 * 2016年2月25日下午2:33:11
 */
public class IdEntityRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

	public IdEntityRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
	}

	public IdEntityRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
	}

	@Override
	public <S extends T> S save(S entity) {
		if (entity instanceof IdEntity) {
			beforeSave((IdEntity) entity);
		}
		return super.save(entity);
	}

	protected void beforeSave(IdEntity entity) {
		Date date = new Date();
		if (entity.getId() == null) {
			entity.setCreatedAt(date);
		}
		entity.setUpdatedAt(date);
	}
}
