package com.feeling.service;

import com.feeling.dao.PageDao;

public class PageService {
	private PageDao pageDao;
	public String findPageCount(String name)
	{
		double count=-1;
		pageDao=new PageDao();
		int n=pageDao.fenPageCount(name);
		count=Math.ceil((double)n/10);
		return String.valueOf((int)count);
		
	}

}
