package com.feeling.service;

import com.alibaba.fastjson.JSON;
import com.feeling.bean.BlackList;
import com.feeling.dao.BlackListDao;
import com.feeling.dao.BlackListUiDao;
import com.feeling.tools.GetNowTime;

public class BlackListUiService {
	private BlackListUiDao blackListUiDao;

	public BlackListUiService() {
		super();
		blackListUiDao=new BlackListUiDao();
	}
	
	public String findBlackListUi(int page)
	{
		String str=JSON.toJSONString(blackListUiDao.finfAllBlackListUi(page));
		return str;
	}
	public String addBlackList(String json)
	{
		String result="error";
		BlackListDao b=new BlackListDao();
		BlackList blackList=JSON.parseObject(json,BlackList.class);
		blackList.blacklist_date=GetNowTime.getNowTime1();
		result=b.addBlackList(blackList);
		return result;
	}
	public String deleteBlackList(String blackListId )
	{
		String result="error";
		BlackListDao b=new BlackListDao();
		int bloguser_id=b.findBlogUserId(Integer.parseInt(blackListId));
		result=b.deleteBlackList(Integer.parseInt(blackListId), bloguser_id);
		return result;
	}
	
	

}
