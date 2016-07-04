package org.appsugar.service.impl;

import java.util.List;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.entity.LongIdEntity;
import org.appsugar.data.jpa.repository.IdEntityRepository;
import org.appsugar.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericServiceImpl<T extends LongIdEntity, C extends LongIdEntityCondition>
		implements GenericService<T, C> {

	@Autowired
	protected IdEntityRepository<T, C> repository;

	@Override
	public T get(Long id) {
		return repository.findOne(id);
	}

	@Override
	public List<T> getAll() {
		return repository.findAll();
	}

	@Override
	public T save(T entity) {
		return repository.save(entity);
	}

	@Override
	public List<T> save(Iterable<T> entities) {
		return repository.save(entities);
	}

	@Override
	public void remove(Long id) {
		repository.delete(id);
	}

	@Override
	public void remove(T entity) {
		repository.delete(entity);
	}

	@Override
	public void remove(Iterable<T> entities) {
		repository.delete(entities);
	}

	@Override
	public Page<T> getByCondition(C condition, Pageable pageable) {
		return repository.findAll(condition, pageable);
	}

	@Override
	public List<T> getByCondition(C condition) {
		return repository.findAll(condition);
	}

}
