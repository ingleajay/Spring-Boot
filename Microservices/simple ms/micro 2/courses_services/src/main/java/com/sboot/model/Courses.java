package com.sboot.model;

public class Courses {
	private Long cid;
	private String cname;
	private String categeory;
	
	private Long stuid;

	public Courses(Long cid, String cname, String categeory, Long stuid) {
		super();
		this.cid = cid;
		this.cname = cname;
		this.categeory = categeory;
		this.stuid = stuid;
	}

	public Courses() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCategeory() {
		return categeory;
	}

	public void setCategeory(String categeory) {
		this.categeory = categeory;
	}

	public Long getStuid() {
		return stuid;
	}

	public void setStuid(Long stuid) {
		this.stuid = stuid;
	}

	@Override
	public String toString() {
		return "Courses [cid=" + cid + ", cname=" + cname + ", categeory=" + categeory + ", stuid=" + stuid + "]";
	}
}
