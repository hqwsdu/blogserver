package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import com.feeling.bean.Blog;
import com.feeling.bean.BlogUser;
import com.feeling.tools.DBtools;

public class BlogDao {
	public String addBlog(Blog blog)
	{
		String str="error";
		String sql="INSERT INTO blog(blog_id,blog_date,blog_content,bloguser_id,blogstate) VALUES(?,?,?,?,'Œ¥…Û∫À')";
		Connection conn=null;
		PreparedStatement pre=null;
		try {
			conn=DBtools.getConnection();
			pre=conn.prepareStatement(sql);
			pre.setInt(1,blog.blog_id);
			pre.setString(2, blog.blog_date);
			pre.setString(3, blog.blog_content);
			pre.setInt(4, blog.bloguser_id);
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
