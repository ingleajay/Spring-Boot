package com.sboot.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

// demo for embedded objects in data

@Document(collection = "students")
public class Student {

    // if u don't want any field to be save in db then use @Transient

    @Id
    private int id;

    private String name;
    private String city;

    private ClassRoom classRoom;

    private List<Subject> subject;

    // for refrences...
    @DBRef
    private Teacher teacher;

    public Student(int id, String name, String city, ClassRoom classRoom, List<Subject> subject,Teacher teacher) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.classRoom = classRoom;
        this.subject = subject;
        this.teacher=teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }


    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", classRoom=" + classRoom +
                ", subject=" + subject +
                '}';
    }

}
