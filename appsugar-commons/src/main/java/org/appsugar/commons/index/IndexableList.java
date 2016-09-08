package org.appsugar.commons.index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import com.google.common.collect.ForwardingList;

/**
 * 可索引的list集合,只支持add,clear,set方法执行数据修改
 * 使用guava 集合装配者模式
 * @author NewYoung
 * 2016年9月8日上午10:43:56
 * @param <T> 索引数据类型
 */
public class IndexableList<T> extends ForwardingList<T> implements Indexable<T> {

	private ManipulateIndexable<T> manipulateIndexable = new ManipulateIndexable<>();

	private List<T> data;

	public IndexableList(List<T> data) {
		super();
		this.data = data;
	}

	/**
	 * 创建一个以ArrayList为存储结构的可索引List
	 */
	public static final <T> IndexableList<T> create() {
		return create(new ArrayList<>());
	}

	/**
	 * 创建一个指定list存储结构的可索引List
	 * @param pointList 指定存储结构,已有的数据不会进行索引
	 */
	public static final <T> IndexableList<T> create(List<T> pointList) {
		return new IndexableList<>(pointList);
	}

	/**
	 *@see Indexable#uniqueIndex(Function) 
	 */
	@Override
	public <K> Index<K, T> uniqueIndex(Function<T, K> keyBuilder) {
		return manipulateIndexable.uniqueIndex(keyBuilder);
	}

	/**
	 * @see Indexable#multipleIndex(Function)
	 */
	@Override
	public <K> Index<K, Collection<T>> multipleIndex(Function<T, K> keyBuilder) {
		return manipulateIndexable.multipleIndex(keyBuilder);
	}

	@Override
	protected List<T> delegate() {
		return data;
	}

	@Override
	public void add(int index, T element) {
		super.add(index, element);
	}

	@Override
	public T remove(int index) {
		T bean = super.remove(index);
		if (Objects.nonNull(bean)) {
			manipulateIndexable.deleteSource(bean);
		}
		return bean;
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
	public T set(int index, T element) {
		T old = super.set(index, element);
		if (Objects.nonNull(old)) {
			manipulateIndexable.deleteSource(old);
		}
		manipulateIndexable.addSource(element);
		return old;
	}

	@Override
	public boolean add(T element) {
		manipulateIndexable.addSource(element);
		return super.add(element);
	}

	@Override
	public void clear() {
		super.clear();
		manipulateIndexable.clear();
	}

}
