package org.appsugar.data.mongo.entity;

import java.util.List;

import org.appsugar.bean.entity.StringIdEntity;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 
 * @author NewYoung
 * 2016年9月22日下午3:25:01
 */
@Document(collection = "xy_person")
public class Person extends StringIdEntity {

	private static final long serialVersionUID = 979523560276070685L;

	public static final String _firstname = "firstname";
	public static final String _lastname = "lastname";
	public static final String _address = "address";
	public static final String _pets = "pets";

	@Field(_firstname)
	private String firstname;

	@Field(_lastname)
	private String lastname;

	@Field(_address)
	private Address address;

	@Field(_pets)
	private List<Pet> pets;

	/**ref query can only use id property**/
	@DBRef
	private Monster monster;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Pet> getPets() {
		return pets;
	}

	public void setPets(List<Pet> pets) {
		this.pets = pets;
	}

	public Monster getMonster() {
		return monster;
	}

	public void setMonster(Monster monster) {
		this.monster = monster;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [firstname=").append(firstname).append(", lastname=").append(lastname)
				.append(", address=").append(address).append(", pets=").append(pets).append(", id=").append(id)
				.append(", createdAt=").append(createdAt).append(", updatedAt=").append(updatedAt).append("]");
		return builder.toString();
	}

}
