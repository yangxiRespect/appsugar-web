package org.appsugar.bean.condition;

/**
 * 
 * @author NewYoung
 * 2016年7月4日下午3:31:22
 */
public class StringIdEntityCondition extends GenericIdEntityCondition<String> {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("StringIdEntityCondition [id=").append(id).append(", startAt=").append(startAt)
				.append(", endAt=").append(endAt).append("]");
		return builder.toString();
	}

}
