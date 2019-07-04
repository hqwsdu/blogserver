package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import com.feeling.bean.Blog;
import com.feeling.bean.ImageFile;
import com.feeling.tools.DBtools;

public class ImageFileDao {
	public String addImageFile(ImageFile img)
	{
		String str="error";
		String sql="INSERT INTO file(file_address,blog_id) VALUES(?,?);";
		Connection conn=null;
		PreparedStatement pre=null;
		try {
			conn=DBtools.getConnection();
			pre=conn.prepareStatement(sql);
			pre.setString(1, img.getFile_address());
			pre.setInt(2, img.getBlog_id());
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
