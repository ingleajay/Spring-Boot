package com.sboot.repository;

import com.sboot.models.ClassRoom;
import com.sboot.models.Student;
import com.sboot.models.Subject;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRespository extends MongoRepository<Student,Integer> {
    List<Student> findByName(String name);
    Student findByNameAndId(String name,Integer id);

   List<Student> findByClassRoomDivision(char division);

   List<Student> findBySubjectSubName(String subName);

   List<Student> findByNameIsLike(String name);

   List<Student> findByNameStartsWith(String name);

   // Native query
    @Query("{\"name\" : \"?0\" }")
   List<Student> getByName(String name);
}
