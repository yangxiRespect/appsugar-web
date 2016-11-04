package org.appsugar.test.data.xml;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

/**
 * xml 数据集
 * @author NewYoung
 * 2016年11月4日上午11:19:22
 */
@JacksonXmlRootElement(localName = "datasets")
public class DataSet {

	@JacksonXmlProperty(isAttribute = true)
	private String name;

	@JacksonXmlElementWrapper(localName = "datalist")
	@JacksonXmlProperty(isAttribute = true)
	private List<Map<String, String>> data;

	public List<Map<String, String>> getData() {
		return data;
	}

	public void setData(List<Map<String, String>> data) {
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DataSet [name=").append(name).append(", data=").append(data).append("]");
		return builder.toString();
	}

}
