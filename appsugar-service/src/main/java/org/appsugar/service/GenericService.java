package org.appsugar.service;

import java.util.List;

import org.appsugar.bean.condition.LongIdEntityCondition;
import org.appsugar.bean.domain.Page;
import org.appsugar.bean.domain.Pageable;
import org.appsugar.bean.domain.Sort;
import org.appsugar.bean.entity.LongIdEntity;

/**
 * 
 * @author NewYoung
 * 2016年2月25日下午6:24:48
 * @param <T>
 */
public interface GenericService<T extends LongIdEntity, C extends LongIdEntityCondition> {

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

	/**
	 * 根据条件分页查询
	 */
	public Page<T> getByCondition(C condition, Pageable pageable);

	/**
	 * 根据条件查询所有
	 */
	public List<T> getByCondition(C condition);

	/**
	 * 根据查询条件查询并排序
	 * @author NewYoung
	 * 2016年11月16日下午12:51:07
	 */
	public List<T> getByCondition(C condition, Sort sort);
}
