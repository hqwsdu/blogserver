package com.feeling.bean;

public class BlackListUi extends BlackList {
	
	private String bloguser_nickname;
	private String bloguser_phone;
	private String bloguser_data;
	private String user_name;
	private String user_permission;
	@Override
	public String toString() {
		return "BlackListUi [bloguser_nickname=" + bloguser_nickname + ", bloguser_phone=" + bloguser_phone
				+ ", bloguser_data=" + bloguser_data + ", user_name=" + user_name + ", user_permission="
				+ user_permission + ", blacklist_id=" + blacklist_id + ", bloguer_id=" + bloguer_id
				+ ", blacklist_date=" + blacklist_date + ", blacklist_cause=" + blacklist_cause + ", user_id=" + user_id
				+ "]";
	}
	public BlackListUi() {
		super();
	}
	public String getBloguser_nickname() {
		return bloguser_nickname;
	}
	public void setBloguser_nickname(String bloguser_nickname) {
		this.bloguser_nickname = bloguser_nickname;
	}
	public String getBloguser_phone() {
		return bloguser_phone;
	}
	public void setBloguser_phone(String bloguser_phone) {
		this.bloguser_phone = bloguser_phone;
	}
	public String getBloguser_data() {
		return bloguser_data;
	}
	public void setBloguser_data(String bloguser_data) {
		this.bloguser_data = bloguser_data;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_permission() {
		return user_permission;
	}
	public void setUser_permission(String user_permission) {
		this.user_permission = user_permission;
	}
	
	

}
