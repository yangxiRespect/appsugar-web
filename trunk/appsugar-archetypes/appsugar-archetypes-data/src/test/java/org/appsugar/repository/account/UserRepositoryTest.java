package org.appsugar.repository.account;

import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.entity.account.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/** 
 * user repository test case
 * @author NewYoung
 * 2016年1月28日下午6:04:19
 */
public class UserRepositoryTest extends BaseJpaDaoTestCase {

	@Autowired
	private UserRepository repository;

	@Test
	public void testFindByName() {
		String name = "admin";
		User user = repository.findByLoginName(name);
		logger.debug("testFindByName  result is : {}", user);
		Assert.assertNotNull(user);
	}

}
