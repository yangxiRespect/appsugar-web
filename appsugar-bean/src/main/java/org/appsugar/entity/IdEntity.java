package org.appsugar.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 
 * @author NewYoung
 * 2016年1月28日下午5:37:02
 */
@MappedSuperclass
public abstract class IdEntity {
	public static final String _id = "id";
	public static final String _createdAt = "createdAt";
	public static final String _updatedAt = "updatedAt";

	//唯一id
	protected Long id;
	//数据创建时间
	protected Date createdAt;
	//数据修改时间
	protected Date updatedAt;

	public IdEntity() {
		super();
	}

	public IdEntity(Long id, Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Column(name = "updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public abstract String toString();

}
