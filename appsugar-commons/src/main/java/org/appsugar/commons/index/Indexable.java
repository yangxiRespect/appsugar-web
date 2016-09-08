package org.appsugar.commons.index;

import java.util.Collection;
import java.util.function.Function;

/**
 * 可索创建引接口
 * @author NewYoung
 * 2016年9月7日下午5:41:39
 * @param <T> 数据类型
 */
public interface Indexable<T> {
	/**
	 * 创建唯一索引
	 * @param keyBuilder 索引key构建器
	 * @return 唯一索引对象
	 */
	public <K> Index<K, T> uniqueIndex(Function<T, K> keyBuilder);

	/**
	 * 创建多值索引
	 * @param keyBuilder  索引key构建器
	 * @return 多值索引对象
	 */
	public <K> Index<K, Collection<T>> multipleIndex(Function<T, K> keyBuilder);
}
