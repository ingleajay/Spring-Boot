package com.sboot.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name="Student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stuid;
	
	@NotBlank(message="Srudent Name field is required !!")
	private String stuname;
	
	@NotBlank(message="Student Email field is required !!")
	private String stuemail;
	
	@NotBlank(message="Password field is required !!")
	private String password;
	
	private String imgurl;
	
	private String role;

	public int getStuid() {
		return stuid;
	}

	public void setStuid(int stuid) {
		this.stuid = stuid;
	}

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public String getStuemail() {
		return stuemail;
	}

	public void setStuemail(String stuemail) {
		this.stuemail = stuemail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgurl() {
		return imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Student(int stuid, @NotBlank(message = "Srudent Name field is required !!") String stuname,
			@NotBlank(message = "Student Email field is required !!") String stuemail,
			@NotBlank(message = "Password field is required !!") String password, String imgurl, String role) {
		super();
		this.stuid = stuid;
		this.stuname = stuname;
		this.stuemail = stuemail;
		this.password = password;
		this.imgurl = imgurl;
		this.role = role;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student [stuid=" + stuid + ", stuname=" + stuname + ", stuemail=" + stuemail + ", password=" + password
				+ ", imgurl=" + imgurl + ", role=" + role + "]";
	}

	

}
