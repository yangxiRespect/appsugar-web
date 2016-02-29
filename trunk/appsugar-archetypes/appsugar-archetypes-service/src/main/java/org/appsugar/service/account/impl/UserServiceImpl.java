package org.appsugar.service.account.impl;

import org.appsugar.condition.account.UserCondition;
import org.appsugar.entity.account.User;
import org.appsugar.repository.account.UserRepository;
import org.appsugar.service.account.UserService;
import org.appsugar.service.impl.GenericServiceImpl;
import org.appsugar.specification.account.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 用户管理实现
 * @author NewYoung
 * 2016年2月26日上午10:45:04
 */
@Service
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User getByLoginName(String loginName) {
		return userRepository.findByLoginName(loginName);
	}

	public UserServiceImpl setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
		return this;
	}

	@Override
	public Page<User> getByCondition(UserCondition condition, Pageable pageable) {
		return userRepository.findAll(new UserSpecification(condition), pageable);
	}

}
