package org.appsugar.condition;

import java.util.Date;

public class IdEntityCondition {

	//id
	protected Long id;
	// 创建起始时间
	protected Date createdStart;
	// 创建结束时间
	protected Date createdEnd;

	public IdEntityCondition() {
		super();
	}

	public IdEntityCondition(Long id, Date createdStart, Date createdEnd) {
		super();
		this.id = id;
		this.createdStart = createdStart;
		this.createdEnd = createdEnd;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedStart() {
		return createdStart;
	}

	public void setCreatedStart(Date createdStart) {
		this.createdStart = createdStart;
	}

	public Date getCreatedEnd() {
		return createdEnd;
	}

	public void setCreatedEnd(Date createdEnd) {
		this.createdEnd = createdEnd;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IdEntityCondition [id=").append(id).append(", createdStart=").append(createdStart)
				.append(", createdEnd=").append(createdEnd).append("]");
		return builder.toString();
	}

}
