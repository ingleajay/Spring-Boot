package com.sboot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sboot.model.Courses;

@Service
public class CoursesServiceImpl implements CoursesService{

	List<Courses> list = List.of(
			
	new Courses(1001L, "DSA" , "CS",101L),
	new Courses(1002L, "CN" , "CS" , 101L),
	new Courses(1002L, "COA" , "EXTC", 102L),
	new Courses(1003L, "ED" , "MEC", 103L)
	
	);
	
	
	@Override
	public List<Courses> getCoursesOfStudent(Long stuid) {
		return list.stream().filter(courses -> courses.getStuid().equals(stuid)).collect(Collectors.toList());
	}

}
