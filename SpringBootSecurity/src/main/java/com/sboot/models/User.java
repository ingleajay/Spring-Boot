package com.sboot.models;

public class User {

	String uname;
	String upass;
	String uemail;
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public String getUemail() {
		return uemail;
	}
	public void setUemail(String uemail) {
		this.uemail = uemail;
	}
	public User(String uname, String upass, String uemail) {
		super();
		this.uname = uname;
		this.upass = upass;
		this.uemail = uemail;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "User [uname=" + uname + ", upass=" + upass + ", uemail=" + uemail + "]";
	}
	
	
}
