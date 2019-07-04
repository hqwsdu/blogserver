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

import com.feeling.bean.BlackListUi;
import com.feeling.bean.BlogUser;
import com.feeling.tools.DBtools;

public class BlackListUiDao {
	private QueryRunner runner;

	public BlackListUiDao() {
		super();
		runner=new QueryRunner();
		
	}
	
	public List<BlackListUi> finfAllBlackListUi(int page)
	{
		int page1=(page-1)*10;
		String sql="SELECT blacklist_id,bloguer_id,blacklist_date,blacklist_cause,blacklist.user_id,bloguser_nickname,bloguser_phone,bloguser_data,user_name,user_permission FROM blacklist INNER JOIN bloguser ON blacklist.bloguer_id=bloguser.bloguser_id INNER JOIN user ON blacklist.user_id=user.user_id limit 10 offset ?";
		Connection connection=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		List<BlackListUi> list = new ArrayList<>(); 
		try {
			 connection=DBtools.getConnection();
			 pre=connection.prepareStatement(sql);
			 pre.setInt(1, page1);
			 rs=pre.executeQuery();
			 while(rs.next())
			 {
				 BlackListUi b=new BlackListUi();
				 b.user_id=rs.getInt("user_id");
				 b.bloguer_id=rs.getInt("bloguer_id");
				 b.blacklist_id=rs.getInt("blacklist_id");
				 b.blacklist_date=rs.getString("blacklist_date");
				 b.blacklist_cause=rs.getString("blacklist_cause");
				 b.setBloguser_data(rs.getString("bloguser_data"));
				 b.setBloguser_nickname(rs.getString("bloguser_nickname"));
				 b.setBloguser_phone(rs.getString("bloguser_phone"));
				 b.setUser_name(rs.getString("user_name"));
				 b.setUser_permission(rs.getString("user_permission"));
				 list.add(b);
				 
				 
			 }
			 
			 
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		finally
		{
			  try {
				rs.close();
				 DbUtils.closeQuietly(connection);
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
			
		}
		
		return list;
		
	}

}
