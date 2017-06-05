package org.appsugar.bean.condition;

import java.util.UUID;

/**
 * 
 * @author NewYoung
 * 2016年7月4日下午3:30:47
 */
public class UUIDEntityCondition extends GenericIdEntityCondition<UUID> {

	private static final long serialVersionUID = 8660413293258710173L;

	@Override
	public String toString() {
		return "UUIDEntityCondition [id=" + id + ", startAt=" + startAt + ", endAt=" + endAt + ", modifyStartAt="
				+ modifyStartAt + ", modifyEndAt=" + modifyEndAt + "]";
	}

}
