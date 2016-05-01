package org.appsugar.service.impl;

import java.util.List;

import org.appsugar.entity.IdEntity;
import org.appsugar.repository.IdEntityRepository;
import org.appsugar.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericServiceImpl<T extends IdEntity> implements GenericService<T> {

	@Autowired
	protected IdEntityRepository<T> repository;

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

}
