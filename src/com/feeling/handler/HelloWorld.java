package com.feeling.handler;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.feeling.bean.User;

@Controller
public class HelloWorld {
	private static final String SUCCESS="error";
	private static final String ERROR="success";
	
	
	@RequestMapping("/helloworld")
	public String hello()
	{
		
		return "main";
	}

	@RequestMapping("/quit")
	public String quit()
	{
		
		return "index";
	}

	@ResponseBody
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(String name,String password,HttpServletResponse re) throws IOException
	{
		
		if(name.equals("zhaotong")&&password.equals("12345"))
		{
			return "redirect:main";
		}
		else
		{
			return "1";
		}
		
	}
	@ResponseBody
	@RequestMapping(value="/login2",method=RequestMethod.POST)
	public String login2(HttpServletRequest req,HttpServletResponse response)
	{
		String json=req.getParameter("data");
		System.out.println(json);
		return SUCCESS;
	}
	

}
