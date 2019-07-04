package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.feeling.bean.BlogUser;
import com.feeling.bean.User;
import com.feeling.tools.DBtools;

public class BlogUserDao {
	private QueryRunner runner;

	public BlogUserDao() {
		super();
		runner=new QueryRunner(); 
	}
	public List<BlogUser> finfAllBlogUser()
	{
		Connection connection=null;
		List<BlogUser> list = new ArrayList<>(); 
		try {
			 connection=DBtools.getConnection();
			 list = runner.query(connection,"SELECT * FROM bloguser ;", new BeanListHandler<>(BlogUser.class));
			 DbUtils.closeQuietly(connection);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		return list;
		
	}
	public List<BlogUser> findPageAllBlogUser(int page) {
		int page1=(page-1)*10;
		Connection connection=null;
		List<BlogUser> list = new ArrayList<>(); 
		try {
			 connection=DBtools.getConnection();
			 list = runner.query(connection,"SELECT * FROM  bloguser WHERE bloguser.bloguser_state='正常' limit 10 offset ? ;", new BeanListHandler<>(BlogUser.class),new String[]{String.valueOf(page1)});
			 //这里是正常的
			 for (BlogUser blogUser : list) {
				 System.out.println("从数据库获取到的用户数据："+blogUser.toString());
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		DbUtils.closeQuietly(connection);
		return list;

	}
	public BlogUser phoneLogin(String phone,String pass)
	{
		BlogUser blogUser=new BlogUser();
		String sql="SELECT * FROM bloguser WHERE bloguser_password=? AND bloguser_phone=?";
		Connection conn=null;
		PreparedStatement pre=null;
		ResultSet re=null;
		try {
			conn=DBtools.getConnection();
			pre=conn.prepareStatement(sql);
			pre.setString(1, pass);
			pre.setString(2, phone);
			re=pre.executeQuery();
			while(re.next())
			{
				blogUser.setBloguser_id(re.getInt(1));
				blogUser.setBloguser_nickname(re.getString(2));
				blogUser.setBloguser_phone(re.getString(3));
				blogUser.setBloguser_password(re.getString(4));
				blogUser.setBloguser_date(re.getString(5));
				blogUser.setBloguser_address(re.getString(6));
				blogUser.setBloguser_state(re.getString(7));
				blogUser.setBloguser_data(re.getString(8));
			}
			re.close();
			
		} catch (SQLException e) {
						e.printStackTrace();
		}
		finally {
			DbUtils.closeQuietly(conn);
		}
		return blogUser;
	}
	public String phoneRegister(BlogUser blogUser)
	{
		String str="error";
		String sql="INSERT INTO bloguser(bloguser_phone,bloguser_password,bloguser_date) VALUES(?,?,?)";
		Connection conn=null;
		PreparedStatement pre=null;
		try {
			conn=DBtools.getConnection();
			pre=conn.prepareStatement(sql);
			pre.setString(1, blogUser.getBloguser_phone());
			pre.setString(2, blogUser.getBloguser_password());
			pre.setString(3, blogUser.getBloguser_date());
			pre.execute();
			str="success";
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(conn);
		}
		
		return str;
		
	}

	

}
