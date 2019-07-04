package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.feeling.bean.LoginLog;
import com.feeling.tools.DBtools;

public class LoginLogDao {
	
	public List<LoginLog> findAllLoginLog(int page)
	{
		String sql="SELECT * FROM loginlog LIMIT 10 OFFSET ?";
		int page1=(page-1)*10;
		List<LoginLog> list=new ArrayList<>();
		Connection conn=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		try {
			conn=DBtools.getConnection();
			pre=conn.prepareStatement(sql);
			pre.setInt(1, page1);
			rs=pre.executeQuery();
			while(rs.next())
			{
				LoginLog log=new LoginLog();
				log.setLoginlog_id(rs.getInt(1));
				log.setUser_name(rs.getString(2));
				log.setLoginlog_date(rs.getString(3));
				list.add(log);
			}
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(conn);
		}
		
		return list;
	}
	public String addLoginLog(String name,String date)
	{
		String result="error";
		String sql="INSERT INTO loginlog(user_name,loginlog_date)VALUES(?,?)";
		Connection conn=null;
		PreparedStatement pre=null;
		try {
			conn=DBtools.getConnection();
			pre=conn.prepareStatement(sql);
			pre.setString(1, name);
			pre.setString(2, date);
			pre.execute();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(conn);
		}
		return result;
		
	}

}
