package org.appsugar.data.mongo.repository;

import java.util.List;

import org.appsugar.data.mongo.domain.CityPersonStat;
import org.appsugar.data.mongo.entity.Person;
import org.appsugar.data.mongo.entity.Pet;

/**
 * @author shenliuyang
 * @email  shenliuyang@gmail.com
 *
 * 2015年8月7日上午10:29:51
 */
public interface PersonRepositoryCustom {

	/**
	 * 修改地址
	 */
	public boolean updatePersonAddress(Person person);

	/**
	 * 根据id修改地址中的城市与街道信息
	 */
	public boolean updatePersonAddressCityAndStreet(String id, String city, String street);

	/**
	 * 给person新增一堆宠物
	 */
	public boolean addPets(String id, Pet... pets);

	/**
	 * 根据名称修改宠物的年龄
	 */
	public boolean updatePetsAgeByName(String id, String petName, Integer newAge);

	/**
	 * 删除指定宠物
	 */
	public boolean removePetsByName(String id, String petName);

	/**
	 * 求每个城市的人数
	 */
	public List<CityPersonStat> groupCityPersons();

}
