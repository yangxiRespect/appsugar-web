package org.appsugar.commons.index;

/**
 * 可修改的索引
 * 可以增加删除修改索引数据
 * @author NewYoung
 * 2016年9月7日下午5:03:32
 * @param <T> 索引数据
 */
public interface UpdatableIndex<T> {
	/**
	 * 增加一个索引数据
	 * @param bean 增加的对象
	 */
	public void addSource(T bean);

	/**
	 * 删除一个索引数据
	 * @param bean 删除对象
	 */
	public void deleteSource(T bean);

	/**
	 * 删除所有索引数据
	 */
	public void clear();
}
