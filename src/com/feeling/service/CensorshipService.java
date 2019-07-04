package com.feeling.service;

import com.alibaba.fastjson.JSON;
import com.feeling.bean.Censorship;
import com.feeling.dao.CensorShipDao;

public class CensorshipService {
	private CensorShipDao censorShipDao;

	public CensorshipService() {
		super();
		censorShipDao=new CensorShipDao();
	}
	public String passBlog(String json)
	{
		Censorship censor=JSON.parseObject(json, Censorship.class);
		String result=censorShipDao.addPassCensorShip(censor);
		return result;
	}
	public String NoPassBlog(String json)
	{
		Censorship censor=JSON.parseObject(json, Censorship.class);
		String result=censorShipDao.addNoPassCensorShip(censor);
		return result;
	}

	

}
