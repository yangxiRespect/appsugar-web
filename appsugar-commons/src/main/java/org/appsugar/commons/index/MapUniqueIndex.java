package org.appsugar.commons.index;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import com.google.common.base.Preconditions;

/**
 * 唯一索引map实现
 * @author NewYoung
 * 2016年9月7日下午4:57:50
 */
public class MapUniqueIndex<K, V> implements Index<K, V>, UpdatableIndex<V> {
	/**
	 * 索引数据存储结构
	 */
	private Map<K, V> data = new HashMap<>();

	/**
	 * key构建器
	 */
	private Function<V, K> keyBuilder;

	/**
	 * 创建一个对象
	 * @param keyBuilder 索引key构建器
	 */
	public static final <K, V> MapUniqueIndex<K, V> create(Function<V, K> keyBuilder) {
		return new MapUniqueIndex<>(keyBuilder);
	}

	public MapUniqueIndex(Function<V, K> keyBuilder) {
		super();
		this.keyBuilder = keyBuilder;
	}

	/**
	 * @see  Index#get(Object)
	 */
	@Override
	public V get(K key) {
		return data.get(key);
	}

	/**
	 * @see Index#ifPresent(Object)
	 */
	@Override
	public boolean ifPresent(K key) {
		return data.containsKey(key);
	}

	/**
	 * 尝试往索引数据中加入 bean. 
	 * @throws IllegalStateException 如果bean对应的键已经存在
	 * @param bean 需要加入的索引数据
	 */
	@Override
	public void addSource(V bean) {
		K key = keyBuilder.apply(bean);
		if (Objects.isNull(key)) {
			return;
		}
		V previous = data.putIfAbsent(key, bean);
		Preconditions.checkState(previous == null,
				"Key not unique in this index key is  %s bean is %s exist bean is %s", key, bean, previous);
	}

	/**
	 *@see  UpdatableIndex#delete(Object)
	 */
	@Override
	public void deleteSource(V bean) {
		K key = keyBuilder.apply(bean);
		if (Objects.isNull(key)) {
			return;
		}
		data.remove(key);
	}

	/**
	 * @see UpdatableIndex#clear()
	 */
	@Override
	public void clear() {
		data.clear();
	}
}
