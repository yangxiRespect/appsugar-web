package org.appsugar.data.mongo.repository;

import java.util.List;

import org.appsugar.data.mongo.BaseMongoTest;
import org.appsugar.data.mongo.domain.CityPersonStat;
import org.appsugar.data.mongo.entity.Address;
import org.appsugar.data.mongo.entity.Person;
import org.appsugar.data.mongo.entity.Pet;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

public class PersonRepositoryTest extends BaseMongoTest {

	@Autowired
	private PersonRepository personRepository;

	@Test
	public void testFindAll() {
		Iterable<Person> persons = personRepository.findAll();
		logger.debug("all person is here {}", persons);
		Assert.assertNotNull(persons);
	}

	@Test
	public void testFindByAddressCityStartingWith() {
		String city = "zhu";
		List<Person> persons = personRepository.findByAddressCityStartingWith(city);
		logger.debug("find person by  city startingWith :{} value is {}", city, persons);
		Assert.assertNotNull(persons);
		Assert.assertFalse(persons.isEmpty());
	}

	@Test
	public void testUpdatePersonAddress() {
		String id = "-1";
		Person person = personRepository.findOne(id);
		Address address = person.getAddress();
		Address newAddress = new Address("洛杉矶", "大马哈");
		person.setAddress(newAddress);
		personRepository.updatePersonAddress(person);
		person = personRepository.findOne(id);
		Assert.assertEquals(person.getAddress().getCity(), newAddress.getCity());
		logger.debug("updated address person is :{}", person);
		person.setAddress(address);
		personRepository.updatePersonAddress(person);
	}

	@Test
	public void testUpdatePersonAddressCityAndStreet() {
		String id = "-2";
		Person person = personRepository.findOne(id);
		Address address = person.getAddress();
		String city = "hell";
		String street = "gohst";
		personRepository.updatePersonAddressCityAndStreet(id, city, street);
		person = personRepository.findOne(id);
		logger.debug("updated person is :{}", person);
		Assert.assertEquals(city, person.getAddress().getCity());
		person.setAddress(address);
		personRepository.save(person);
	}

	@Test
	public void testFindPersonAddressById() {
		String id = "-1";
		Person person = personRepository.findAddressById(id);
		logger.debug("address is {}", person);
	}

	@Test
	public void testGroupCityPersons() {
		List<CityPersonStat> result = personRepository.groupCityPersons();
		logger.debug(" stat is {}", result);
	}

	@Test
	public void testAddPets() {
		String id = "-1";
		Pet[] pets = { new Pet("xiaohua", 23), new Pet("xiaowei", 20) };
		boolean result = personRepository.addPets(id, pets);
		Assert.assertTrue("add pets error", result);
		Person person = personRepository.findOne(id);
		logger.debug("after add pets value is : {}", person);
	}

	@Test
	public void testUpdatePetsAgeByName() {
		String id = "-2";
		List<Pet> pets = personRepository.findOne(id).getPets();
		logger.debug("ordinary pets is : {}", pets);
		personRepository.updatePetsAgeByName(id, "dog", 85);
		pets = personRepository.findOne(id).getPets();
		logger.debug("new pets is : {}", pets);
	}

	@Test
	public void testRemovePetsByName() {
		String id = "-1";
		Pet[] pets = { new Pet("xx", 1) };
		personRepository.addPets(id, pets);
		List<Pet> petList = personRepository.findOne(id).getPets();
		logger.debug("ordinary pets is {}", petList);
		personRepository.removePetsByName(id, "xx");
		petList = personRepository.findOne(id).getPets();
		logger.debug("new pets is {}", petList);
	}

	@Test
	public void testExample() {
		Person condition = new Person();
		condition.setId("-1");
		Person result = personRepository.findOne(Example.of(condition));
		logger.debug("testExample condition {} result {}", condition, result);
	}
}
