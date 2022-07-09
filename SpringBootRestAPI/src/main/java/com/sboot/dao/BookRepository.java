package com.sboot.dao;

import org.springframework.data.repository.CrudRepository;

import com.sboot.models.Books;

public interface BookRepository extends CrudRepository<Books,Integer>{
public Books findById(int id);
}
