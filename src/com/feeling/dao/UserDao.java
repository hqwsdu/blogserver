package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.tribes.util.Arrays;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.feeling.bean.User;
import com.feeling.tools.DBtools;

public class UserDao {
	private QueryRunner runner;
	
	public UserDao() {
		super();
		runner=new QueryRunner(); 
	}

	public List<User> findAllUser() {
		Connection connection=null;
		List<User> list = new ArrayList<>(); 
		try {
			 connection=DBtools.getConnection();
			 list = runner.query(connection,"SELECT * FROM  user limit 10 offset 0;", new BeanListHandler<>(User.class));
			 DbUtils.closeQuietly(connection);
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		return list;

	}
	public User findUserForName(String name) {
		User user = new User();
		Connection connection=null;
		try {
			connection=DBtools.getConnection();
			user=runner.query(connection,"SELECT * FROM user WHERE user_name=?",new BeanHandler<User>(User.class),new String[]{name});
			 DbUtils.closeQuietly(connection);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return user;

	}
	public String addUser(User user){
		Connection connection=null;
		String sql="INSERT INTO user (user_name,user_password,user_registered_date,user_permission,user_data)"
				+ "VALUES(?,?,?,?,?)";
		
		try {
			connection=DBtools.getConnection();
			User user1=runner.insert(connection,sql, new BeanHandler<>(User.class),
			            new String[]{user.getUser_name(),user.getUser_password(),user.getUser_registered_date(),user.getUser_permission(),user.getUser_data()});
			DbUtils.closeQuietly(connection);
			return "success";
		    
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return "error";
		
		
		
	}
	public List<User> findPageAllUser(int page) {
		int page1=(page-1)*10;
		Connection connection=null;
		List<User> list = new ArrayList<>(); 
		try {
			 connection=DBtools.getConnection();
			 list = runner.query(connection,"SELECT * FROM  user limit 10 offset ?;", new BeanListHandler<>(User.class),new String[]{String.valueOf(page1)});
			 
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		DbUtils.closeQuietly(connection);
		return list;

	}
	public String deleteFromIdUser(int id)
	{
		String sql="DELETE  FROM user WHERE user_id=?";

		String result="error";
		Connection connection=null;
		try {
			connection=DBtools.getConnection();
			runner.update(connection,sql, id);
		
			return "success";
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		DbUtils.closeQuietly(connection);
	
		return result;
	}
	public int findUserForId(String name)
	{
		String sql="SELECT user_id FROM user WHERE user_name='"+name+"'";
		Connection conn=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		int id=-1;
		
		try {
			conn=DBtools.getConnection();
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next())
			{
				id=rs.getInt("user_id");
				System.out.println("asdasd"+rs.getInt(1));
				
			}
			rs.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally
		{
			DbUtils.closeQuietly(conn);
		}
		
		
		return id;
		
	}

	
	
	

}
