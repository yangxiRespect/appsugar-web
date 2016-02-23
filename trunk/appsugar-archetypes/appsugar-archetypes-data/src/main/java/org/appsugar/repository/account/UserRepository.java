package org.appsugar.repository.account;

import org.appsugar.entity.account.User;
import org.appsugar.repository.IdEntityRepository;

/**
 * 
 * @author NewYoung
 * 2016年2月22日下午1:00:51
 */
public interface UserRepository extends IdEntityRepository<User> {
	/**
	 * find user by loginName  eq
	 */
	public User findByLoginName(String name);
}
