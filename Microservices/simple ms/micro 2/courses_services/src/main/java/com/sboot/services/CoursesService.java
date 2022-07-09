package com.sboot.services;

import java.util.List;

import com.sboot.model.Courses;

public interface CoursesService {

	public List<Courses> getCoursesOfStudent(Long stuid); 
}
