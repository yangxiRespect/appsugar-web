package org.appsugar.bean.condition;

import java.io.Serializable;
import java.util.Date;

/**
 * id eq
 * createdAt ge startAt
 * createdAt lt endAt
 * @author NewYoung
 * 2016年7月4日下午3:27:43
 * @param <ID>
 */
public abstract class GenericIdEntityCondition<ID extends Serializable> {

	protected ID id;
	protected Date startAt;
	protected Date endAt;

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

	@Override
	public abstract String toString();
}
