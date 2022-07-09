package com.sboot.models;

public class Citizen {

	private int id;
	private String name;
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
