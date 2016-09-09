package org.appsugar.commons.index;

import java.util.Collection;
import java.util.Objects;
import java.util.function.Function;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;

/**
 * 一键多值map结构实现
 * 采用guava Multimap实现
 * @author NewYoung
 * 2016年9月7日下午4:41:35
 */
public class MapMultipleIndex<K, V> implements Index<K, Collection<V>>, UpdatableIndex<V> {

	/**
	 * 索引数据存储结构
	 */
	private Multimap<K, V> data = HashMultimap.create();

	/**
	 * key构建器
	 */
	private Function<V, K> keyBuilder;

	/**
	 * 创建一个对象
	 * @param keyBuilder 索引key构建器
	 */
	public static final <K, V> MapMultipleIndex<K, V> create(Function<V, K> keyBuilder) {
		return new MapMultipleIndex<>(keyBuilder);
	}

	public MapMultipleIndex(Function<V, K> keyBuilder) {
		super();
		this.keyBuilder = keyBuilder;
	}

	/**
	 *@see  Index#get(Object)
	 */
	@Override
	public Collection<V> get(K key) {
		return data.get(key);
	}

	/**
	 *@see Index#ifPresent(Object)
	 */
	@Override
	public boolean ifPresent(K key) {
		return data.containsKey(key);
	}

	/**
	 * @see UpdatableIndex#add(Object)
	 */
	@Override
	public void addSource(V bean) {
		K key = keyBuilder.apply(bean);
		if (Objects.isNull(key)) {
			return;
		}
		data.put(key, bean);
	}

	/**
	 * @see UpdatableIndex#delete(Object)
	 */
	@Override
	public void deleteSource(V bean) {
		K key = keyBuilder.apply(bean);
		if (Objects.isNull(key)) {
			return;
		}
		data.remove(key, bean);
	}

	/**
	 * @see UpdatableIndex#clear()
	 */
	@Override
	public void clear() {
		data.clear();
	}
}
