package com.feeling.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.feeling.bean.BlogUser;
import com.feeling.bean.User;
import com.feeling.dao.BlogUserDao;
import com.feeling.tools.GetNowTime;

public class BlogUserService {
	private BlogUserDao blogUserDao;

	public BlogUserService() {
		super();
		blogUserDao=new BlogUserDao();
	}
	public String  findAllUser()
	{
		String str="";
		List<BlogUser> list=blogUserDao.finfAllBlogUser();
		str=JSON.toJSONString(list);
		return str;
		
	}
	public String findPageBlogUser(String page)
	{
		int p=Integer.parseInt(page);
		
		List<BlogUser> list=blogUserDao.findPageAllBlogUser(p);
		for (BlogUser blogUser : list) {
			 System.out.println("从Service获得的数据："+blogUser.toString());
		}
		String str=JSON.toJSONString(list);
		System.out.println("转为字符串的："+str);
		return str;
	}
	public String phoneLogin(String phone,String pass)
	{
		BlogUser b=blogUserDao.phoneLogin(phone, pass);
		String json=JSON.toJSONString(b);
		return json;
	}
	public String phoneAddBlogUser(String phone,String pass)
	{
		String str="error";
		BlogUser blogUser=new BlogUser();
		blogUser.setBloguser_phone(phone);
		blogUser.setBloguser_password(pass);
		blogUser.setBloguser_date(GetNowTime.getNowTime1());
		str=blogUserDao.phoneRegister(blogUser);
		return str;
		
	}
	
	
	

}
