package com.feeling.service;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.feeling.bean.Blog;
import com.feeling.bean.ImageFile;
import com.feeling.dao.BlogDao;
import com.feeling.dao.BlogUiDao;
import com.feeling.dao.ImageFileDao;
import com.feeling.tools.GetNowTime;

public class BlogService {
	private BlogUiDao blogUiDao;

	public BlogService() {
		super();
		blogUiDao=new BlogUiDao();
	}
	public String findAllBlog(String page)
	{
		String json;
		int page1=Integer.parseInt(page);
		json=JSON.toJSONString(blogUiDao.findAllBlogUi(page1));
		return json;
		
	}
	public String findNoCheckBlog(String page)
	{
		String json;
		int page1=Integer.parseInt(page);
		json=JSON.toJSONString(blogUiDao.findNoCheckBlogUi(page1));
		return json;
		
	}
	public String findMyAllBlog(String blog_userid)
	{
		String json;
		int page1=Integer.parseInt(blog_userid);
		json=JSON.toJSONString(blogUiDao.findMyBlogUi(page1));
		return json;
		
	}
	public String findCateBlog(String type)
	{
		String json;
		
		json=JSON.toJSONString(blogUiDao.findCateBlog(type));
		return json;
		
	}
	public String addBlog(Blog blog)
	{
		String result="error";
		blog.blog_date=GetNowTime.getNowTime1();
		BlogDao blogDao=new BlogDao();
		result=blogDao.addBlog(blog);
		return result;
		
	}
	public String addImagFile(List<ImageFile> list)
	{
		String str="error";
		ImageFileDao dao=new ImageFileDao();
		for(int i=0;i<list.size();i++)
		{
			dao.addImageFile(list.get(i));
		}
		return str;
	}

	

}
