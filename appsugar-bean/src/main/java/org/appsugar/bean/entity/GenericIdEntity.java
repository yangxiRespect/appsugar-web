package org.appsugar.bean.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

/**
 * 
 * @author NewYoung
 * 2016年1月28日下午5:37:02
 */
@MappedSuperclass
public abstract class GenericIdEntity<ID extends Serializable> implements Serializable {
	private static final long serialVersionUID = -608692408189530550L;

	public static final String _createdAt = "createdAt";
	public static final String _updatedAt = "updatedAt";
	//数据创建时间

	protected Date createdAt;
	//数据修改时间

	protected Date updatedAt;

	@CreatedDate
	@Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@LastModifiedDate
	@Column(name = "updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * id身份
	 */
	public abstract ID identification();

	@Override
	public abstract String toString();

	@Override
	public abstract int hashCode();

	@Override
	public abstract boolean equals(Object obj);
}
