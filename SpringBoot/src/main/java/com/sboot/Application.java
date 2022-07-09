package com.sboot;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.sboot.dao.PersonRepository;
import com.sboot.models.Person;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
	ConfigurableApplicationContext context	= SpringApplication.run(Application.class, args);
	PersonRepository pr = context.getBean(PersonRepository.class);
	
	// single person add
	//Person p1  = new Person(101,"Ajay","Mumbai");
	//pr.save(p1);
	
	// multiple person add
//	Person p1  = new Person(102,"Vijay","Delhi");
//	Person p2  = new Person(103,"Sajay","Mumbai");
//	Person p3  = new Person(104,"jay","Baglore");
//	List<Person> persons = List.of(p1,p2,p3);
//	pr.saveAll(persons);
	
	// Get specific person
	Optional<Person> p1 = pr.findById(10);
	Person p = p1.get();
	System.out.println(p);
	
	// update specific person use above and below
//	p.setPname("Mehak");
//	pr.save(p);
//	System.out.println(p);
	
	// get data all by id
	List<Integer> ids= List.of(2,4,5);
	List<Person> pids = (List<Person>) pr.findAllById(ids);
	System.out.println(pids);
	
	// delete data by id
	//pr.deleteById(2);
	
	// get all data 
		Iterable<Person> ps = pr.findAll();
		ps.forEach((i)->{System.out.println(i.getPid() + " " + i.getPcity() + " " + i.getPname());});
	
	// get name person
		List<Person> lpname = pr.findByPname("Vijay");
		System.out.println(lpname);
		
	// get name and city of person
		List<Person> pc = pr.findByPnameAndPcity("Sajay", "Mumbai");
		System.out.println(pc);

		// order name by finding city in descending 
		List<Person> pc1 = pr.findByPcityOrderByPnameDesc("Mumbai");
		System.out.println(pc1);
		
		// jpql - get all person
		List<Person> ps1 = pr.getAllPerson();
		ps1.forEach((i)->{System.out.println(i.getPid() + " " + i.getPcity() + " " + i.getPname());});
		
		// jpql - get name person
		List<Person> pname1= pr.getPersonByName("Vijay");
		System.out.println(pname1);
		
		// jpql - get name and city of person
		List<Person> pnc = pr.getPersonByNameAndByCity("Sajay","Mumbai");
		System.out.println(pnc);
		
		// native query - get all person
		List<Person> ps2 = pr.getallPerson();
		ps2.forEach((i)->{System.out.println(i.getPid() + " " + i.getPcity() + " " + i.getPname());});
	}
}
