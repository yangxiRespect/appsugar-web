package org.appsugar.data.mongo.domain;

public class CityPersonStat {

	private String city;

	private Long personCount;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getPersonCount() {
		return personCount;
	}

	public void setPersonCount(Long personCount) {
		this.personCount = personCount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CityPersonStat [city=").append(city).append(", personCount=").append(personCount).append("]");
		return builder.toString();
	}

}
