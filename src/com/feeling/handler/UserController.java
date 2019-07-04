package com.feeling.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feeling.service.LoginLogService;
import com.feeling.service.UserService;
@RequestMapping("/UserController")
@Controller
public class UserController {
	
	@ResponseBody
	@RequestMapping(value="/findAllUser",method=RequestMethod.POST)
	public String findAllUser(HttpServletRequest request,HttpServletResponse response)
	{
		
		UserService s=new UserService();
		String str=s.findAllUserJson();
		return str;
	}
	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String findUserForName(HttpServletRequest request,HttpSession session)
	{
		String result="";
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(username+""+password);
		UserService s=new UserService();
		result=s.findUserForName(username, password);
		
		if(result=="success")
		{
			String id=s.findUserId(username);
			session.setAttribute("user_id", id);
		    session.setAttribute("username",username );
			session.setAttribute("password", password);
			LoginLogService s1=new LoginLogService();
			s1.addLoginLog(username);
		}
		return result;
	}
	@ResponseBody
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	public String addUser(HttpServletRequest request)
	{
		String json=request.getParameter("fdata");
		UserService s=new UserService();
		String re=s.addUser(json);
		
		return re;
	}
	@ResponseBody
	@RequestMapping(value="/findPageUser",method=RequestMethod.POST)
	public String findPageUser(HttpServletRequest request)
	{
		String page=request.getParameter("data");
		UserService s=new UserService();
		String str="";
		str=s.findPageUser(page);
		return str;
		
	}
	@ResponseBody
	@RequestMapping(value="/deleteUserFromId",method=RequestMethod.POST)
	public String deleteUserFromId(HttpServletRequest request)
	{
		String result ="";
		String id=request.getParameter("id");
		UserService s=new UserService();
		result=s.deleteUserFromId(id);
		return result;
		
		
	}

}
