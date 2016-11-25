package org.appsugar.data.mongo.repository.speicification;

import org.apache.commons.lang3.StringUtils;
import org.appsugar.data.common.repository.querydsl.PredicateCollection;
import org.appsugar.data.mongo.domain.PersonCondition;
import org.appsugar.data.mongo.entity.QPerson;
import org.appsugar.data.mongo.repository.querdsl.MongoQueryDslSpecification;
import org.springframework.stereotype.Component;

/**
 * 人物查询器
 * @author NewYoung
 * 2016年11月25日下午3:17:36
 */
@Component
public class PersonMongoSpecification extends MongoQueryDslSpecification<PersonCondition, QPerson> {

	@Override
	public void toPredicate(PredicateCollection pc, PersonCondition condition, QPerson root) {
		super.toPredicate(pc, condition, root);
		String petName = condition.getPetName();
		if (StringUtils.isNotBlank(petName)) {
			pc.add(root.pets.any().name.startsWith(petName));
		}
		String city = condition.getCity();
		if (StringUtils.isNotBlank(city)) {
			pc.add(root.address.city.eq(city));
		}
	}

}
