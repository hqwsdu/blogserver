package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import com.feeling.tools.DBtools;

public class MainTableDao {
	private int[] table_count=new int[5];
	private String[] str={"user","bloguser","blog"};
	
	public int[] findAllTableCount()
	{
		for(int i=0;i<3;i++)
		{
			table_count[i]=findTableCount(str[i]);
		}
		table_count[3]=findNoCheckCount();
		
		
		return table_count;
	}
	private int findTableCount(String nameTable)
	{
		int count=0;
		String sql="SELECT count(*) FROM ";
		Connection conn=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		try {
			conn=DBtools.getConnection();
			pre=conn.prepareStatement(sql+nameTable);
			rs=pre.executeQuery();
			while(rs.next())
			{
				count=rs.getInt(1);
			}
			rs.close();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(conn);
		}
		
		
		return count;
		
	}
	private int findNoCheckCount()
	{
		int count=0;
		String sql="SELECT count(*) FROM blog WHERE blogstate='Î´ÉóºË'";
		Connection conn=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		try {
			conn=DBtools.getConnection();
			pre=conn.prepareStatement(sql);
			rs=pre.executeQuery();
			while(rs.next())
			{
				count=rs.getInt(1);
			}
			rs.close();
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(conn);
		}
		
		
		return count;
	}

}
