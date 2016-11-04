package org.appsugar.test.data;

import java.util.List;
import java.util.Map;

/**
 * 示例测试数据集
 * @author NewYoung
 * 2016年11月4日下午1:47:58
 */
public interface SampleDataSet {

	/**
	 * 获取有序 数据集名称
	 */
	public List<String> orderedDataSetNameList();

	/**
	 * 根据数据集名称获取所有数据
	 */
	public List<Map<String, String>> getDataListByDataSetName(String name);
}
