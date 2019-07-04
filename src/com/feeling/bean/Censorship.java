package com.feeling.bean;

public class Censorship {
	private int censorship_id;
	private int blog_id ;
	private int user_id ;
	private String censorship_result;
	private String ccensorship_date;
	public int getCensorship_id() {
		return censorship_id;
	}
	public void setCensorship_id(int censorship_id) {
		this.censorship_id = censorship_id;
	}
	public Censorship() {
		super();
	}
	public int getBlog_id() {
		return blog_id;
	}
	public void setBlog_id(int blog_id) {
		this.blog_id = blog_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getCensorship_result() {
		return censorship_result;
	}
	public void setCensorship_result(String censorship_result) {
		this.censorship_result = censorship_result;
	}
	public String getCcensorship_date() {
		return ccensorship_date;
	}
	public void setCcensorship_date(String ccensorship_date) {
		this.ccensorship_date = ccensorship_date;
	}
	
}
