package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;

import com.feeling.bean.BlackList;
import com.feeling.tools.DBtools;
import com.feeling.tools.GetNowTime;

public class BlackListDao
 {
	
	private QueryRunner runner;

	public BlackListDao() {
		super();
		runner=new QueryRunner();
	}
	public String addBlackList(BlackList blackList)
	{
		String result="error";
		String sql1="INSERT INTO  blacklist(bloguer_id,user_id,blacklist_date,blacklist_cause) VALUES(?,?,?,?)";
		String sql2="UPDATE bloguser SET bloguser_state='黑名单' WHERE bloguser_id=?";
		Connection conn=null;
		PreparedStatement pre=null;
		
        
		try {
			
			conn=DBtools.getConnection();
			conn.setAutoCommit(false);
			pre=conn.prepareStatement(sql1);
			pre.setInt(1,blackList.bloguer_id );
			pre.setInt(2, blackList.user_id);
			pre.setString(3,blackList.blacklist_date);
			pre.setString(4, blackList.blacklist_cause);
			pre.executeUpdate();
			pre=conn.prepareStatement(sql2);
			pre.setInt(1,blackList.bloguer_id);
			pre.executeUpdate();
			conn.commit();
			result="success";
			
			
			
		} catch (SQLException e) {
			
			
				try {
					conn.rollback();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			
		}
		finally
		{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			DbUtils.closeQuietly(conn);
		}
		
		return result;
	}
	public String deleteBlackList(int blackListId,int blogUserId)
	{
		String result ="error";
		String sql1="UPDATE bloguser SET bloguser_state='正常' WHERE bloguser_id=?";
		String sql2="DELETE FROM blacklist WHERE blacklist_id= ?";
		Connection connection=null;
		PreparedStatement pre=null;
		try {
			connection=DBtools.getConnection();
			connection.setAutoCommit(false);
			pre=connection.prepareStatement(sql1);
			pre.setInt(1, blogUserId);
			pre.executeUpdate();
			pre=connection.prepareStatement(sql2);
			pre.setInt(1, blackListId);
			pre.executeUpdate();
			connection.commit();
			result="success";
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				
			}
			
		}
		finally {
			try {
				connection.setAutoCommit(true);
				DbUtils.closeQuietly(connection);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
		}
		
		
		return result;
		
	}
	public int findBlogUserId(int blacklistid)
	{
		int blogUserId=-1;
		String sql="SELECT bloguer_id FROM blacklist WHERE blacklist_id=?";

		Connection coon=null;
		try {
			 coon=DBtools.getConnection();
			PreparedStatement pres=coon.prepareStatement(sql);
			pres.setInt(1, blacklistid);
			ResultSet rs=pres.executeQuery();
			while(rs.next())
			{
			   return rs.getInt(1);
			}
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			DbUtils.closeQuietly(coon);
		}
		
		
		return blogUserId;
		
	}

}
