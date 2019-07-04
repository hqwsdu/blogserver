package com.feeling.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.feeling.bean.User;
import com.feeling.dao.UserDao;
import com.feeling.tools.GetNowTime;

public class UserService {

	private UserDao userDao;

	public UserService() {
		super();
		userDao = new UserDao();
	}

	public String findAllUserJson() {
		List<User> list = userDao.findAllUser();
		String str = JSON.toJSONString(list);
		return str;
	}

	public String findUserForName(String name, String password) {
		User user=null;
	      user = userDao.findUserForName(name);
		if(user!=null)
		{
			if (user.getUser_name().equals(name) && user.getUser_password().equals(password)) {
				return "success";
			} 
			else
			{
				return "error";
			}
		}
		else {
			return "error";
		}

	}
	public String addUser(String json)
	{
		User user=JSON.parseObject(json, User.class);
		user.setUser_registered_date(GetNowTime.getNowTime1());
		String result=userDao.addUser(user);
		if(result.equals("error"))
		{
			return "error";
		}
		
		
		return "success";
	}
	public String findPageUser(String page)
	{
		int p=Integer.parseInt(page);
		
		List<User> list=userDao.findPageAllUser(p);
		String str=JSON.toJSONString(list);
		return str;
	}
	public String deleteUserFromId(String id)
	{
		String result="";
		result=userDao.deleteFromIdUser(Integer.parseInt(id));
		return result;
	}
	public String findUserId(String name)
	{
		String str=null;
		str=String.valueOf(userDao.findUserForId(name));
		return str;
	}

}
