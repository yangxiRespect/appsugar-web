package org.appsugar.bean.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.MappedSuperclass;

/**
 * compatibility for data jpa
 * @author NewYoung
 * 2016年7月4日下午3:15:28
 */
@MappedSuperclass
public abstract class LongIdEntity extends GenericIdEntity<Long> {
	private static final long serialVersionUID = -1391277931996151818L;

	public static final String _id = "id";
	protected Long id;

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long identification() {
		return this.id;
	}
}
