package org.appsugar.entity.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.appsugar.entity.IdEntity;

@Entity
@Table(name = "as_user")
public class User extends IdEntity {

	public static final String _name = "name";
	public static final String _loginName = "loginName";
	public static final String _password = "password";

	private String name;
	private String loginName;
	private String password;

	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "login_name", unique = true)
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=").append(name).append(", loginName=").append(loginName).append(", password=")
				.append(password).append("]");
		return builder.toString();
	}

}
