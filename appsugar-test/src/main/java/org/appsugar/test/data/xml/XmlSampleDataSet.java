package org.appsugar.test.data.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.appsugar.test.data.SampleDataSet;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * 基于jackson实现的xml格式sampledata
 * @author NewYoung
 * 2016年11月4日下午2:08:59
 */
public class XmlSampleDataSet implements SampleDataSet {

	private List<DataSet> dataList;

	public XmlSampleDataSet(InputStream in) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new XmlMapper();
		dataList = mapper.readValue(in, new TypeReference<List<DataSet>>() {
		});
	}

	@Override
	public List<String> orderedDataSetNameList() {
		return dataList.stream().map(DataSet::getName).collect(Collectors.toList());
	}

	@Override
	public List<Map<String, String>> getDataListByDataSetName(String name) {
		return dataList.stream().filter(e -> Objects.equals(e.getName(), name)).findFirst().get().getData();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("XmlSampleDataSet [dataList=").append(dataList).append("]");
		return builder.toString();
	}

}
