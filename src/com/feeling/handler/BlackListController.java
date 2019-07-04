package com.feeling.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feeling.service.BlackListUiService;
@RequestMapping("/BlackListController")
@Controller
public class BlackListController {
	
	
	@ResponseBody
	@RequestMapping(value="/findBalckListUi",method=RequestMethod.POST)
	public String findBalckListUi(HttpServletRequest request)
	{
		BlackListUiService s=new BlackListUiService();
		String page=request.getParameter("data");
		String str=s.findBlackListUi(Integer.parseInt(page));
		return str;
	}
	@ResponseBody
	@RequestMapping(value="/addBlackList",method=RequestMethod.POST)
	public String addBlackList(HttpServletRequest request)
	{
		String result="error";
		String json=request.getParameter("data");
		System.out.println(json);
		BlackListUiService s=new BlackListUiService();
		result=s.addBlackList(json);
		
		
		return result;
		
	}
	@ResponseBody
	@RequestMapping(value="/deleteBlackList",method=RequestMethod.POST)
	public String deleteBlackList(HttpServletRequest request)
	{
		String result="error";
		String id_1=request.getParameter("id_1");
		
		
		
		BlackListUiService s=new BlackListUiService();
		result=s.deleteBlackList(id_1);
		return result;
	}

}
