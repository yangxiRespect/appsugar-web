package org.appsugar.commons.index;

/**
 * 数据索引
 * 根据键快速查询结果
 * @author NewYoung
 * 2016年9月7日下午4:27:31
 * @param <K> 索引建的类型
 * @param <V> 被索引的值类型
 */
public interface Index<K, V> {
	/**
	 * 根据键获取对应的值
	 * @param key 键
	 * @return 根据key对应的值反之null
	 */
	public V get(K key);

	/**
	 * 判断当前索引键是否存在
	 * @param key 需要判断的所有键
	 * @return 如果根据键找到对应的值那么返回true反之false
	 */
	public boolean ifPresent(K key);
}
