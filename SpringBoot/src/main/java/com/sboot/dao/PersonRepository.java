package com.sboot.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sboot.models.Person;

public interface PersonRepository extends CrudRepository<Person,Integer>{

	// customize query
	public List<Person> findByPname(String pname);
	public List<Person> findByPnameAndPcity(String name, String city);
	public List<Person> findByPcityOrderByPnameDesc(String city);
	
	// JPQL = java persistance query language
	@Query("select p from Person p")
	public List<Person> getAllPerson();
	
	@Query("select x from Person x where x.pname=:n")
	public List<Person> getPersonByName(@Param("n") String name);
	
	@Query("select x from Person x where x.pname=:n and x.pcity=:c")
	public List<Person> getPersonByNameAndByCity(@Param("n") String name , @Param("c") String city);
	
	// native query 
	@Query(value="select * from person ",nativeQuery = true)
	public List<Person> getallPerson();
}
