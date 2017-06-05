package org.appsugar.bean.condition;

/**
 * 
 * @author NewYoung
 * 2016年7月4日下午3:30:47
 */
public class LongIdEntityCondition extends GenericIdEntityCondition<Long> {

	private static final long serialVersionUID = -8482230326885005235L;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LongIdEntityCondition [id=").append(id).append(", startAt=").append(startAt).append(", endAt=")
				.append(endAt).append("]");
		return builder.toString();
	}

}
