package com.feeling.bean;

public class User {
	public User(int user_id, String user_name, String user_password, String user_registered_date,
			String user_permission, String user_data) {
		
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_registered_date = user_registered_date;
		this.user_permission = user_permission;
		this.user_data = user_data;
	}
	private int user_id;
	private String user_name;
	private String user_password;
	private String user_registered_date;
	private String user_permission;
	private String user_data;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_registered_date() {
		return user_registered_date;
	}
	public void setUser_registered_date(String user_registered_date) {
		this.user_registered_date = user_registered_date;
	}
	public String getUser_permission() {
		return user_permission;
	}
	public void setUser_permission(String user_permission) {
		this.user_permission = user_permission;
	}
	public String getUser_data() {
		return user_data;
	}
	public User() {
		super();
	}
	public void setUser_data(String user_data) {
		this.user_data = user_data;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_name=" + user_name + ", user_password=" + user_password
				+ ", user_registered_date=" + user_registered_date + ", user_permission=" + user_permission
				+ ", user_data=" + user_data + "]";
	}
	

}
