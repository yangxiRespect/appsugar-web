package org.appsugar.bean.condition;

import java.io.Serializable;
import java.util.Date;

/**
 * id eq
 * createdAt ge startAt
 * createdAt le endAt
 * updatedAt ge modifyStartAt
 * updatedAt le modifyEndAt
 * @author NewYoung
 * 2016年7月4日下午3:27:43
 */
public abstract class GenericIdEntityCondition<ID extends Serializable> {

	protected ID id;
	protected Date startAt;
	protected Date endAt;
	protected Date modifyStartAt;
	protected Date modifyEndAt;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public Date getStartAt() {
		return startAt;
	}

	public void setStartAt(Date startAt) {
		this.startAt = startAt;
	}

	public Date getEndAt() {
		return endAt;
	}

	public void setEndAt(Date endAt) {
		this.endAt = endAt;
	}

	public Date getModifyStartAt() {
		return modifyStartAt;
	}

	public void setModifyStartAt(Date modifyStartAt) {
		this.modifyStartAt = modifyStartAt;
	}

	public Date getModifyEndAt() {
		return modifyEndAt;
	}

	public void setModifyEndAt(Date modifyEndAt) {
		this.modifyEndAt = modifyEndAt;
	}

	@Override
	public abstract String toString();
}
