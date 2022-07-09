package com.sboot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Citizen {

    @Id	
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column
	private String name;
	
	@Column
	private int vcenterid;

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

	public int getVcenterid() {
		return vcenterid;
	}

	public void setVcenterid(int vcenterid) {
		this.vcenterid= vcenterid;
	}

	public Citizen(int id, String name,int vcenterid) {
		super();
		this.id = id;
		this.name = name;
		this.vcenterid = vcenterid;
	}

	public Citizen() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Citizen [id=" + id + ", name=" + name + ", vcenterid=" + vcenterid + "]";
	}
	
	
}
