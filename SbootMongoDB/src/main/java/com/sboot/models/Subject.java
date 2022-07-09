package com.sboot.models;

import org.springframework.data.mongodb.core.mapping.Document;

//@Document(collection = "subjects")
public class Subject {
    private Integer subId;
    private String subName;
    private float subMarks;

    public Subject(Integer subId, String subName, float subMarks) {
        this.subId = subId;
        this.subName = subName;
        this.subMarks = subMarks;
    }

    public Integer getSubId() {
        return subId;
    }


    public void setSubId(Integer subId) {
        this.subId = subId;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subId=" + subId +
                ", subName='" + subName + '\'' +
                ", subMarks=" + subMarks +
                '}';
    }
}
