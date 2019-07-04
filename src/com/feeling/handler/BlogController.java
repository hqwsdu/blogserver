package com.feeling.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feeling.service.BlogService;
import com.feeling.service.CensorshipService;

@RequestMapping("/BlogController")
@Controller
public class BlogController {
	
	
	
	@ResponseBody
	@RequestMapping(value="/findAllBlog",method=RequestMethod.POST)
	public String findAllBlog(HttpServletRequest request)
	{
		String json;
		String page=request.getParameter("data");
		BlogService blog=new BlogService();
		json=blog.findAllBlog(page);
		//***********************
				//System.out.println("控制层的数据findAllBlog："+json);
		return json;
		
	}
	@ResponseBody
	@RequestMapping(value="/findMyAllBlog",method=RequestMethod.POST)
	public String findMyAllBlog(HttpServletRequest request)
	{
		String json;
		String bloguserid=request.getParameter("data");
		BlogService blog=new BlogService();
		json=blog.findMyAllBlog(bloguserid);
		return json;
		
	}
	@ResponseBody
	@RequestMapping(value="/findCateBlog",method=RequestMethod.POST)
	public String findCateBlog(HttpServletRequest request)
	{
		String json;
		String type=request.getParameter("data");
		BlogService blog=new BlogService();
		json=blog.findCateBlog(type);
		return json;
		
	}
	@ResponseBody
	@RequestMapping(value="/findNoCheckBlog",method=RequestMethod.POST)
	public String findNoCheckBlog(HttpServletRequest request)
	{
		String json;
		String page=request.getParameter("data");
		BlogService blog=new BlogService();
		json=blog.findNoCheckBlog(page);
		return json;
		
	}
	@ResponseBody
	@RequestMapping(value="/passBlog",method=RequestMethod.POST)
	public String passBlog(HttpServletRequest request)
	{
		String result="error";
		CensorshipService s=new CensorshipService();
		String data=request.getParameter("data");
		result=s.passBlog(data);
		return result;
		
		
	}
	@ResponseBody
	@RequestMapping(value="/NoPassBlog",method=RequestMethod.POST)
	public String NoPassBlog(HttpServletRequest request)
	{
		String result="error";
		CensorshipService s=new CensorshipService();
		String data=request.getParameter("data");
		result=s.NoPassBlog(data);
		return result;
		
	}

}
