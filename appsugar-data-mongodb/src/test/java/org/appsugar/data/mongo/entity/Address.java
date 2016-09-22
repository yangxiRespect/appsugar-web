package org.appsugar.data.mongo.entity;

import java.io.Serializable;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @author shenliuyang
 * @email  shenliuyang@gmail.com
 *
 * 2015年8月7日上午10:45:51
 */
public class Address implements Serializable {
	private static final long serialVersionUID = 4417851553609060375L;

	public static final String _city = "city";
	public static final String _street = "street";

	@Field(_city)
	private String city;

	@Field(_street)
	private String street;

	public Address() {
		super();
	}

	public Address(String city, String street) {
		super();
		this.city = city;
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Address [city=").append(city).append(", street=").append(street).append("]");
		return builder.toString();
	}

}
