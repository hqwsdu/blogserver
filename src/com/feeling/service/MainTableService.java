package com.feeling.service;

import com.alibaba.fastjson.JSON;
import com.feeling.dao.MainTableDao;

public class MainTableService {
	private MainTableDao mainTableDao;

	public MainTableService() {
		super();
		mainTableDao=new MainTableDao();
	}
	public String findAllTableCount()
	{
		String json=null;
		json=JSON.toJSONString(mainTableDao.findAllTableCount());
		
		return json;
		
	}
	

}
