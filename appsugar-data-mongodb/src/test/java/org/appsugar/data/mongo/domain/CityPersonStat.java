package org.appsugar.data.mongo.domain;

public class CityPersonStat {

	//type safe
	public static final String _city = "city";
	public static final String _personCount = "personCount";

	private String city;

	private Long personCount;

	public CityPersonStat() {
		super();
	}

	public CityPersonStat(String city, Long personCount) {
		super();
		this.city = city;
		this.personCount = personCount;
	}

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
