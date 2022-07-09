package com.sboot.repository;

import com.sboot.models.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher,Long> {
}
