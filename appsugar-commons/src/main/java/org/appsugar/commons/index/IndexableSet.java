package org.appsugar.commons.index;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.ForwardingSet;

/**
 * 可索引的set数据结构, remove,add,clear会触发索引构建
 * 修改对象索引时,先remove对象再add进来
 * @author NewYoung
 * 2016年9月8日上午11:32:17
 */
public class IndexableSet<T> extends ForwardingSet<T> implements Indexable<T> {
	private ManipulateIndexable<T> manipulateIndexable = new ManipulateIndexable<>();
	private Set<T> data;

	public IndexableSet(Set<T> data) {
		super();
		this.data = data;
	}

	/**
	 * 创建以hashset为默认存储结构的对象
	 */
	public static final <T> IndexableSet<T> create() {
		return create(new HashSet<>());
	}

	/**
	 * 创建以指定set存储结构的对象
	 */
	public static final <T> IndexableSet<T> create(Set<T> data) {
		return new IndexableSet<>(data);
	}

	@Override
	protected Set<T> delegate() {
		return data;
	}

	@Override
	public <K> Index<K, T> uniqueIndex(Function<T, K> keyBuilder) {
		return manipulateIndexable.uniqueIndex(keyBuilder);
	}

	@Override
	public <K> Index<K, Collection<T>> multipleIndex(Function<T, K> keyBuilder) {
		return manipulateIndexable.multipleIndex(keyBuilder);
	}

	@Override
	public boolean add(T element) {
		boolean result = super.add(element);
		if (result) {
			manipulateIndexable.addSource(element);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object object) {
		boolean result = super.remove(object);
		if (result) {
			manipulateIndexable.deleteSource((T) object);
		}
		return result;
	}

	@Override
	public void clear() {
		super.clear();
		manipulateIndexable.clear();
	}

}
