package com.sboot.models;

import org.springframework.stereotype.Component;

// this is my response class

// college want to build ID for each student 


@Component
public class College {

	private int cid;
	private String cname;
	private String clocation;
	private String stuname;
	private int stuid;
	
	
	
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public int getStuid() {
		return stuid;
	}
	public void setStuid(int stuid) {
		this.stuid = stuid;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getClocation() {
		return clocation;
	}
	public void setClocation(String clocation) {
		this.clocation = clocation;
	}
	
	
	public College(int cid, String cname, String clocation, String stuname, int stuid) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.clocation = clocation;
		this.stuname = stuname;
		this.stuid = stuid;
	}
	public College() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "College [cid=" + cid + ", cname=" + cname + ", clocation=" + clocation + "]";
	}
	
	
}
