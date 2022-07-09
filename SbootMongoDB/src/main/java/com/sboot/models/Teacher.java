package com.sboot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/// demo for refrence data

@Document(collection = "teacher")
public class Teacher {

    @Transient
    public static final String SEQUENCE_NAME = "teachers_sequence";

    @Id
    private Long id;
    private String tname;

    public Long getId() {
        return id;
    }

    public String getTname() {
        return tname;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public Teacher(Long id, String tname) {
        this.id = id;
        this.tname = tname;
    }
}
