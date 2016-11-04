package org.appsugar.data.xml;

import org.appsugar.test.data.SampleDataSet;
import org.appsugar.test.data.xml.XmlSampleDataSet;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlSampleDataSetTest {

	private static Logger logger = LoggerFactory.getLogger(XmlSampleDataSetTest.class);

	@Test
	public void testLoad() throws Exception {
		SampleDataSet dataSet = new XmlSampleDataSet(
				Thread.currentThread().getContextClassLoader().getResourceAsStream("sample-data.xml"));
		logger.debug("data set is {}", dataSet);
		Assert.assertNotNull(dataSet);
	}
}
