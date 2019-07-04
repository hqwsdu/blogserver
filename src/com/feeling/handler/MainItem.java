package com.feeling.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feeling.service.LoginLogService;
import com.feeling.service.MainTableService;

@Controller
public class MainItem {
	private static final String MAIN_LOGINLOG="login_log";
	private static final String MAIN_TABLE="main_table";
	private static final String MAIN_CHECKLOG="checkblog";
    private static final String MAIN_HEIMDAN="heimingdan";
    private static final String MIAN_ADDUSER="main_adduser";
    private static final String MAIN_BLOGDATA="main_blogdata";
    private static final String MAIN_USERDATA="main_userdata";
    private static final String MAIN_USERDATA2="main_userdata2";
    
    //服务器当前信息
	@RequestMapping("/main_table")
	public String main_table()
	{
		return MAIN_TABLE;
	}
	@ResponseBody
	@RequestMapping(value="/findAllLoginLog",method=RequestMethod.POST)
	public String findAllLoginLog(HttpServletRequest request)
	{
		String json=null;
		String page=request.getParameter("data");
		LoginLogService s=new LoginLogService();
		json=s.findALlLoginLog(page);
		
		return json;
	}

	  
    //获取table信息
	@ResponseBody
	@RequestMapping(value="/findTable",method=RequestMethod.POST)
	public String findTable()
	{
		String json=null;
		MainTableService s=new MainTableService();
		json=s.findAllTableCount();
		return json;
	}
	@RequestMapping("/main_guanliyuan")
	public String main_guanliyuan()
	{
		return MAIN_USERDATA;
	}
	@RequestMapping("/add_guanliyuan")
	public String add_guanliyuan()
	{
		return MIAN_ADDUSER;
	}
	@RequestMapping("/findUser")
	public String findUser()
	{
		return MAIN_USERDATA2;
	}
	@RequestMapping("/heiMingDan")
	public String heiMingDan()
	{
		return MAIN_HEIMDAN;
	}
	@RequestMapping("/findBlog")
	public String findBlog()
	{
		 return MAIN_BLOGDATA;
	}
	@RequestMapping("/checkBlog")
	public String checkBlog()
	{
		return MAIN_CHECKLOG;
	}
	@RequestMapping("/loginLog")
	public String loginLog()
	{
		return MAIN_LOGINLOG;
	}
	

}
