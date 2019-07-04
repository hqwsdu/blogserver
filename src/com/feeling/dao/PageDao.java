package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.dbutils.DbUtils;

import com.feeling.tools.DBtools;

public class PageDao {
	private static final String sql="SELECT count(*) from ";
	public int  fenPageCount(String name)
	{
		
		
		Connection coon=null;
		try {
			 coon=DBtools.getConnection();
			PreparedStatement pres=coon.prepareStatement(sql+name);
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
		
		
		return 1;
		
	}

}
