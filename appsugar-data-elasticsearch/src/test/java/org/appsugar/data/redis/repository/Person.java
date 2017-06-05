package org.appsugar.data.redis.repository;

import org.appsugar.bean.entity.StringIdEntity;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author NewYoung
 * 2016年10月31日下午5:53:27
 */
@Document(indexName = "person")
public class Person extends StringIdEntity {
	private static final long serialVersionUID = -6357881072446357297L;

	private String name;
	private String phone;
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Person [id=").append(id).append(", createdAt=").append(createdAt).append(", updatedAt=")
				.append(updatedAt).append(", name=").append(name).append(", phone=").append(phone).append(", email=")
				.append(email).append("]");
		return builder.toString();
	}

}
