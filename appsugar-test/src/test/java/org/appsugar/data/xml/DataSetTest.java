package org.appsugar.data.xml;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.appsugar.test.data.xml.DataSet;
import org.junit.Test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * xml数据集序列化测试
 * @author NewYoung
 * 2016年11月4日上午11:21:14
 */
public class DataSetTest {

	@Test
	public void testSerialization() throws Exception {
		XmlMapper mapper = new XmlMapper();
		DataSet set = new DataSet();
		Map<String, String> entity = new HashMap<>();
		entity.put("name", "NewYoung");
		entity.put("age", "25");
		set.setName("cccx.xxx");
		set.setData(Arrays.asList(entity));
	}

	@Test
	public void testDeserialization() throws Exception {
		ObjectMapper mapper = new XmlMapper();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("sample-data.xml");
		List<DataSet> data = mapper.readValue(in, new TypeReference<List<DataSet>>() {
		});
	}
}
