package org.appsugar.data.mongo.domain;

import org.appsugar.bean.condition.StringIdEntityCondition;

/**
 * 
 * @author NewYoung
 * 2016年11月25日下午3:00:55
 */
public class PersonCondition extends StringIdEntityCondition {
	/**宠物名称:前置模糊匹配**/
	private String petName;
	/**城市:eq**/
	private String city;

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PersonCondition [petName=").append(petName).append(", city=").append(city).append("]");
		return builder.toString();
	}

}
