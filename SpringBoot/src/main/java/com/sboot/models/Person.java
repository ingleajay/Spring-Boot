package com.sboot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int pid;
	
	public String pname;
	
	public String pcity;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPcity() {
		return pcity;
	}

	public void setPcity(String pcity) {
		this.pcity = pcity;
	}

	public Person(int pid, String pname, String pcity) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pcity = pcity;
	}
	
	public Person() {
		super();
	}

	@Override
	public String toString() {
		return "Person [pid=" + pid + ", pname=" + pname + ", pcity=" + pcity + "]";
	}

}
