package com.feeling.service;

import com.alibaba.fastjson.JSON;
import com.feeling.dao.LoginLogDao;
import com.feeling.tools.GetNowTime;

public class LoginLogService {
	private LoginLogDao dao;

	public LoginLogService() {
		super();
		dao=new LoginLogDao();
	}
	public String findALlLoginLog(String page)
	{
		String json=null;
		int page1=Integer.parseInt(page);
		json=JSON.toJSONString(dao.findAllLoginLog(page1));
		return json;
		
	}
	public String addLoginLog(String name)
	{
		String result="error";
		String time=GetNowTime.getNowTime1();
		dao.addLoginLog(name, time);
		
		return result;
	}
	

}
