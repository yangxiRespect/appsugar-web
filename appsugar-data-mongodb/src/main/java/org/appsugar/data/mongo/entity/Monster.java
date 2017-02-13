package org.appsugar.data.mongo.entity;

import org.appsugar.bean.entity.StringIdEntity;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "xy_monster")
public class Monster extends StringIdEntity {
	private static final long serialVersionUID = -707427532856081600L;

	@Field("name")
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return null;
	}

}
