package org.appsugar.bean.entity;

import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.Id;

/**
 * compatibility for mangodb
 * @author NewYoung
 * 2016年7月4日下午3:13:26
 */
@MappedSuperclass
public abstract class StringIdEntity extends GenericIdEntity<String> {
	private static final long serialVersionUID = -2794260915057323784L;

	public static final String _id = "id";
	protected String id;

	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**
	 * id身份
	 */
	@Override
	public String identification() {
		return this.id;
	}
}
