package org.appsugar.data.solr.repository;

import org.appsugar.data.solr.BaseSolrTest;
import org.appsugar.data.solr.entity.Person;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户solr数据接口测试
 * @author NewYoung
 * 2016年11月7日下午2:26:25
 */
public class PersonSolrRepositoryTest extends BaseSolrTest {

	@Autowired
	private PersonSolrRepository repository;

	@Test
	public void testFindByName() {
		String name = "NewYoung";
		Person person = repository.findByName(name);
		logger.debug("testFindByName name is {} result is {}", name, person);
	}

}
