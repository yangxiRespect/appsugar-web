package org.appsugar.service;

import java.util.List;

import org.appsugar.entity.IdEntity;

/**
 * 
 * @author NewYoung
 * 2016年2月25日下午6:24:48
 * @param <T>
 */
public interface GenericService<T extends IdEntity> {

	/**
	 * 根据id获取
	 */
	public T get(Long id);

	/**
	 * 获取所有
	 */
	public List<T> getAll();

	/**
	 * 保存或修改
	 */
	public T save(T entity);

	/**
	 * 批量保存
	 */
	public List<T> save(Iterable<T> entities);

	/**
	 * 根据id删除
	 */
	public void remove(Long id);

	/**
	 * 删除
	 */
	public void remove(T entity);

	/**
	 * 批量删除
	 */
	public void remove(Iterable<T> entities);

}
