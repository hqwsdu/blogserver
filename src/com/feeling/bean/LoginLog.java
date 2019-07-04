package com.feeling.bean;

public class LoginLog {
	private int loginlog_id;
	private String user_name;
	private String loginlog_date ;
	public int getLoginlog_id() {
		return loginlog_id;
	}
	public void setLoginlog_id(int loginlog_id) {
		this.loginlog_id = loginlog_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getLoginlog_date() {
		return loginlog_date;
	}
	public void setLoginlog_date(String loginlog_date) {
		this.loginlog_date = loginlog_date;
	}
	public LoginLog() {
		super();
	}
	

}
