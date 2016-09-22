package org.appsugar.test.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(value = { "classpath*:/applicationContext-resources.xml", "classpath*:/applicationContext.xml",
		"classpath*:/applicationContext*.xml" })
public class SpringBaseTest extends AbstractJUnit4SpringContextTests {

	protected Logger logger = LoggerFactory.getLogger(SpringBaseTest.class);

}
