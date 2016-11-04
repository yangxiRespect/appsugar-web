package org.appsugar.data.mongo.repository;

import java.util.List;

import org.appsugar.bean.entity.StringIdEntity;
import org.appsugar.data.mongo.Paths;
import org.appsugar.data.mongo.domain.CityPersonStat;
import org.appsugar.data.mongo.entity.Address;
import org.appsugar.data.mongo.entity.Person;
import org.appsugar.data.mongo.entity.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 
 * @author shenliuyang
 * @email  shenliuyang@gmail.com
 *
 * 2015年8月7日上午10:35:07
 */
public class PersonRepositoryImpl implements PersonRepositoryCustom {

	private MongoOperations operation;

	private static final String addressCityPath = Paths.join(Person._address, Address._city);
	private static final String addressStreetPath = Paths.join(Person._address, Address._street);

	public PersonRepositoryImpl() {
		super();
	}

	@Autowired
	public void setOperation(MongoOperations operation) {
		this.operation = operation;
	}

	@Override
	public boolean updatePersonAddress(Person person) {
		return operation.updateFirst(Query.query(Criteria.where(StringIdEntity._id).is(person.getId())),
				Update.update(Person._address, person.getAddress()), Person.class).getN() > 0;
	}

	@Override
	public boolean updatePersonAddressCityAndStreet(String id, String city, String street) {
		return operation.updateFirst(Query.query(Criteria.where(StringIdEntity._id).is(id)),
				Update.update(addressCityPath, city).set(addressStreetPath, street), Person.class).getN() > 0;
	}

	@Override
	public List<CityPersonStat> groupCityPersons() {
		TypedAggregation<Person> aggregation = Aggregation.newAggregation(Person.class,
				Aggregation.group(addressCityPath).count().as(CityPersonStat._personCount).addToSet(addressCityPath)
						.as(CityPersonStat._city),
				Aggregation.project(CityPersonStat._personCount).and(CityPersonStat._city).previousOperation());
		return operation.aggregate(aggregation, CityPersonStat.class).getMappedResults();
	}

	@Override
	public boolean addPets(String id, Pet... pets) {
		//pushAll不推荐使用了 maven的mongodb集成插件还只支持到2.2.1，所以push each会出问题
		return operation.updateFirst(Query.query(Criteria.where(StringIdEntity._id).is(id)),
				new Update().push(Person._pets).each((Object[]) pets), Person.class).getN() > 0;
	}

	private static final String dynamicPetAgeColumn = Paths.join(Person._pets, "$", Pet._age);

	@Override
	public boolean updatePetsAgeByName(String id, String petName, Integer newAge) {
		//修改整个对象 Update.update("pets.$", new Pet(xxx,xxx))
		return operation.updateFirst(
				Query.query(Criteria.where(StringIdEntity._id).is(id).and(Person._pets)
						.elemMatch(Criteria.where(Pet._name).is(petName))),
				Update.update(dynamicPetAgeColumn, newAge), Person.class).getN() > 0;
	}

	@Override
	public boolean removePetsByName(String id, String petName) {
		//pull是通过query进行移除pull(Person._pets,CriteriaObject)， pullAll是通过对象匹配进行移除 pullAll(Person._pets,Pet[])
		return operation.updateFirst(Query.query(Criteria.where(StringIdEntity._id).is(id)),
				new Update().pull(Person._pets, Criteria.where(Pet._name).is(petName).getCriteriaObject()),
				Person.class).getN() > 0;
	}
}
