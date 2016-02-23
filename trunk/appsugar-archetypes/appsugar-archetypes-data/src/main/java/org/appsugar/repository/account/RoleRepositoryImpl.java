package org.appsugar.repository.account;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author NewYoung
 * 2016年2月23日下午6:57:48
 */
public class RoleRepositoryImpl implements RoleRepositoryCustom {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void deleteRole(Long id) {
		userRepository.deleteRoleRelationship(id);
		roleRepository.delete(id);
	}

}
