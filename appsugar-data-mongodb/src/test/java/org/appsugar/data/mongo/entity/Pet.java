package org.appsugar.data.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Field;

/**
 * 宠物
 * @author NewYoung
 * @email  shenliuyang@gmail.com
 * 2015年8月17日下午1:30:04
 */
public class Pet {
	public static final String _name = "name";
	public static final String _age = "age";
	@Field(_name)
	private String name;

	@Field(_age)
	private Integer age;

	public Pet() {
		super();
	}

	public Pet(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pet [name=").append(name).append(", age=").append(age).append("]");
		return builder.toString();
	}

}
