package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import com.feeling.bean.Censorship;
import com.feeling.tools.DBtools;

public class CensorShipDao {
	
	private String sql_yes="UPDATE blog SET blogstate='审核通过' WHERE blog_id=?";
	private String sql_no="UPDATE blog SET blogstate='审核不通过' WHERE blog_id=?";
	
	
	public String addPassCensorShip(Censorship cen)
	{
		String result=addCensor(cen, sql_yes);
		return result;
		
	}
	public String addNoPassCensorShip(Censorship cen)
	{
		String result=addCensor(cen, sql_no);
		return result;
		
	}
	private String addCensor(Censorship cen,String sql2)
	{
		String result="error";
		String sql1="INSERT INTO censorship (blog_id ,ccensorship_date,user_id ,censorship_result)VALUES(?,?,?,?)";
		
		Connection conn = null;
		PreparedStatement pre=null;
		try {
			conn=DBtools.getConnection();
			conn.setAutoCommit(false);
			pre=conn.prepareStatement(sql1);
			pre.setInt(1, cen.getBlog_id());
			pre.setString(2, cen.getCcensorship_date());
			pre.setInt(3, cen.getUser_id());
			pre.setString(4, cen.getCensorship_result());
			pre.executeUpdate();
			
			pre=conn.prepareStatement(sql2);
			pre.setInt(1, cen.getBlog_id());
			pre.executeUpdate();
			
			conn.commit();
			result="success";
			
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
								e1.printStackTrace();
			}
						e.printStackTrace();
		}
		finally
		{
			try {
				conn.setAutoCommit(true);
				DbUtils.closeQuietly(conn);;
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		
		
		
		return result;
	}
	
	

}
