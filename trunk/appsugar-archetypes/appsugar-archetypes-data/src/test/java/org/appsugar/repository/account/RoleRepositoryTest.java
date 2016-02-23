package org.appsugar.repository.account;

import org.appsugar.BaseJpaDaoTestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午6:50:59
 */
public class RoleRepositoryTest extends BaseJpaDaoTestCase {

	@Autowired
	private RoleRepository repository;

	@Test
	public void testDeleteRole() {
		Long id = -2l;
		repository.deleteRole(id);
	}

}
