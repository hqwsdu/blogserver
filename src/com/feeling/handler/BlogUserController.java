package com.feeling.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feeling.service.BlogUserService;
import com.feeling.service.UserService;
@RequestMapping("/BlogUserController")
@Controller
public class BlogUserController {
	
	@ResponseBody
	@RequestMapping(value="/findAllBlogUser")
	public String findAllBlogUser()
	{
		BlogUserService service=new BlogUserService();
		String str=service.findAllUser();
		//***********************
		System.out.println("控制层的数据："+str);
		return str;
	}
	@ResponseBody
	@RequestMapping(value="/findPageBlogUser",method=RequestMethod.POST)
	public String findPageBlogUser(HttpServletRequest request)
	{
		String page=request.getParameter("data");
	    BlogUserService s=new BlogUserService();
		String str="";
		str=s.findPageBlogUser(page);
		//***********************
		System.out.println("控制层的数据："+str);
		return str;
		
	}
	@ResponseBody
	@RequestMapping(value="phoneLogin",method=RequestMethod.GET)
	public String phoneLogin(HttpServletRequest request)
	{
		String phone=request.getParameter("phone");
		String pass=request.getParameter("pass");
	    BlogUserService s=new BlogUserService();
		String str="";
		str=s.phoneLogin(phone, pass);
		return str;
		
	}
	@ResponseBody
	@RequestMapping(value="phoneRegister",method=RequestMethod.GET)
	public String phoneRegister(HttpServletRequest request)
	{
		String phone=request.getParameter("phone");
		String pass=request.getParameter("pass");
	    BlogUserService s=new BlogUserService();
		String str="";
		str=s.phoneAddBlogUser(phone, pass);
		return str;
		
	}

}
