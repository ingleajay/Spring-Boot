package com.sboot.models;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

//@Document(collection = "classes")
public class ClassRoom {

    @Field(name="Standard")
    private String className;
    private char division;

    public ClassRoom(char division){
        this.division = division;
    }

    public ClassRoom(){
        super();
    }

    public ClassRoom(String className, char division) {
        this.className = className;
        this.division = division;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public char getDivision() {
        return division;
    }

    public void setDivision(char division) {
        this.division = division;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "className='" + className + '\'' +
                ", division='" + division + '\'' +
                '}';
    }
}
