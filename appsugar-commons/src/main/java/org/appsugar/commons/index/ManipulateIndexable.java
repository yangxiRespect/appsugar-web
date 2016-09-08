package org.appsugar.commons.index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * 手动维护可索引接口
 * @author NewYoung
 * 2016年9月7日下午6:03:24
 * @param <T>
 */
public class ManipulateIndexable<T> implements Indexable<T>, UpdatableIndex<T> {

	private List<UpdatableIndex<T>> indexList = new ArrayList<>();

	/**
	 * @see UpdatableIndex#clear()
	 */
	@Override
	public void clear() {
		indexList.forEach(e -> e.clear());
	}

	/**
	 * 向所有索引中增加指定对象
	 */
	@Override
	public void addSource(T bean) {
		indexList.forEach(e -> e.addSource(bean));
	}

	/**
	 * 向所有索引中删除指定对象
	 */
	@Override
	public void deleteSource(T bean) {
		indexList.forEach(e -> e.deleteSource(bean));
	}

	/**
	 * @see Indexable#uniqueIndex(Function)
	 */
	@Override
	public <K> Index<K, T> uniqueIndex(Function<T, K> keyBuilder) {
		MapUniqueIndex<K, T> index = MapUniqueIndex.create(keyBuilder);
		indexList.add(index);
		return index;
	}

	/**
	 * @see Indexable#multipleIndex(Function)
	 */
	@Override
	public <K> Index<K, Collection<T>> multipleIndex(Function<T, K> keyBuilder) {
		MapMultipleIndex<K, T> index = MapMultipleIndex.create(keyBuilder);
		indexList.add(index);
		return index;
	}

}
