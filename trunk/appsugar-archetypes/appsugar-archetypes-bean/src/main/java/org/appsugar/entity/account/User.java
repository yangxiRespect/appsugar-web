package org.appsugar.entity.account;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.appsugar.entity.IdEntity;

/**
 * 用户
 * @author NewYoung
 * 2016年2月23日下午6:08:56
 */
@Entity
@Table(name = "as_user")
public class User extends IdEntity {

	public static final String _name = "name";
	public static final String _loginName = "loginName";
	public static final String _password = "password";
	public static final String _roleList = "roleList";
	public static final String _permissionList = "permissionList";

	//名称
	private String name;
	//登录名
	private String loginName;
	//密码
	private String password;
	//角色
	private List<Role> roleList;
	//权限
	private List<String> permissionList;

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

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "as_user_role", joinColumns = @JoinColumn(name = "user_id") , inverseJoinColumns = @JoinColumn(name = "role_id") )
	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	@ElementCollection(fetch = FetchType.LAZY)
	@CollectionTable(name = "as_user_permission", joinColumns = @JoinColumn(name = "user_id") )
	@Column(name = "permission")
	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [name=");
		builder.append(name);
		builder.append(", loginName=");
		builder.append(loginName);
		builder.append(", password=");
		builder.append(password);
		builder.append(", id=");
		builder.append(id);
		builder.append(", createdAt=");
		builder.append(createdAt);
		builder.append(", updatedAt=");
		builder.append(updatedAt);
		builder.append("]");
		return builder.toString();
	}

}
