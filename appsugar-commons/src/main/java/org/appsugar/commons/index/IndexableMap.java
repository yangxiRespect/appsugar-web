package org.appsugar.commons.index;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import com.google.common.collect.ForwardingMap;

/**
 * 可索引Map数据结构,put,clear,remove方法会触发索引构建
 * @author NewYoung
 * 2016年9月8日下午1:07:26
 */
public class IndexableMap<K, V> extends ForwardingMap<K, V> implements Indexable<V> {
	private ManipulateIndexable<V> manipulateIndexable = new ManipulateIndexable<>();
	private Map<K, V> data;

	public IndexableMap(Map<K, V> data) {
		super();
		this.data = data;
	}

	/**
	 * 创建一个默认以hashmap存储的对象
	 * @return
	 */
	public static final <K, V> IndexableMap<K, V> create() {
		return create(new HashMap<>());
	}

	/**
	 * 创建指定map存储结构的对象
	 */
	public static final <K, V> IndexableMap<K, V> create(Map<K, V> data) {
		return new IndexableMap<>(data);
	}

	@Override
	public <S> Index<S, V> uniqueIndex(Function<V, S> keyBuilder) {
		return manipulateIndexable.uniqueIndex(keyBuilder);
	}

	@Override
	public <S> Index<S, Collection<V>> multipleIndex(Function<V, S> keyBuilder) {
		return manipulateIndexable.multipleIndex(keyBuilder);
	}

	@Override
	protected Map<K, V> delegate() {
		return data;
	}

	@Override
	public V remove(Object object) {
		V v = super.remove(object);
		if (Objects.nonNull(v)) {
			manipulateIndexable.deleteSource(v);
		}
		return v;
	}

	@Override
	public void clear() {
		super.clear();
		manipulateIndexable.clear();
	}

	@Override
	public V put(K key, V value) {
		V v = super.put(key, value);
		if (Objects.nonNull(v)) {
			manipulateIndexable.deleteSource(v);
		}
		manipulateIndexable.addSource(value);
		return v;
	}

}
