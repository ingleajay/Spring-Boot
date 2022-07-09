package com.sboot.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vaccination {

	@Id
	private int id;
	
	@Column
	private String vname;
	
	@Column
	private String vaddr;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVname() {
		return vname;
	}

	public void setVname(String vname) {
		this.vname = vname;
	}

	public String getVaddr() {
		return vaddr;
	}

	public void setVaddr(String vaddr) {
		this.vaddr = vaddr;
	}

	public Vaccination(int id, String vname, String vaddr) {
		super();
		this.id = id;
		this.vname = vname;
		this.vaddr = vaddr;
	}

	public Vaccination() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Vaccination [id=" + id + ", vname=" + vname + ", vaddr=" + vaddr + "]";
	}
}
