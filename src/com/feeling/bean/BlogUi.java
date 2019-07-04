package com.feeling.bean;

import java.util.List;

public class BlogUi extends Blog{
	private String bloguser_nickname;
	private List<ImageFile> list;

	public List<ImageFile> getList() {
		return list;
	}

	public void setList(List<ImageFile> list) {
		this.list = list;
	}

	public String getBloguser_nickname() {
		return bloguser_nickname;
	}

	public void setBloguser_nickname(String bloguser_nickname) {
		this.bloguser_nickname = bloguser_nickname;
	}

	public BlogUi() {
		super();
	}
	

}
