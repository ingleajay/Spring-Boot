package com.sboot.blog.controller;

import com.sboot.blog.entity.Role;
import com.sboot.blog.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/api/v1/roles")
    public ResponseEntity setRoles(@RequestBody Role roles){
        return new ResponseEntity(this.roleRepository.save(roles), HttpStatus.CREATED);
    }
}

