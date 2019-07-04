package com.feeling.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feeling.service.PageService;
@RequestMapping("/PageController")
@Controller
public class PageController {
	
	
	@ResponseBody
	@RequestMapping(value="/findPageCount",method=RequestMethod.POST)
	public String findPageCount(HttpServletRequest request)
	{
	   PageService pa=new PageService();
	   String str=request.getParameter("name");
	   
	   String result=pa.findPageCount(str);
	   
		return result;
	}

}
