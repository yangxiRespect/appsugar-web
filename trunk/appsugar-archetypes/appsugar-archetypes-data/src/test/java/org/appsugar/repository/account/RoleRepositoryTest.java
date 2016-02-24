package org.appsugar.repository.account;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.appsugar.BaseJpaDaoTestCase;
import org.appsugar.dto.account.RoleUserDto;
import org.junit.Assert;
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

	@Test
	public void testGetRoleStatistic() {
		List<RoleUserDto> roleUserDtoList = repository.getRoleStatistic();
		logger.debug("testGetRoleStatistic result is {}", roleUserDtoList);
		Assert.assertTrue(CollectionUtils.isNotEmpty(roleUserDtoList));
	}

	@Test
	public void testFindRoleStatistic() {
		List<RoleUserDto> roleUserDtoList = repository.findRoleStatistic(-2l);
		logger.debug("testFindRoleStatistic result is {}", roleUserDtoList);
		Assert.assertTrue(CollectionUtils.isNotEmpty(roleUserDtoList));
	}
}
