package com.feeling.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;

import com.feeling.bean.BlogUi;
import com.feeling.bean.ImageFile;
import com.feeling.tools.DBtools;

public class BlogUiDao {
	
	public List<BlogUi> findMyBlogUi(int bloguser_id)
	{
		List<BlogUi> list=new ArrayList<>();
		String str="SELECT blog.blog_id,blog.blog_date,blog.blog_content,blog.bloguser_id,blog.blogstate,blog.blogtype,bloguser.bloguser_nickname FROM blog ,bloguser WHERE bloguser.bloguser_id=blog.bloguser_id AND blog.bloguser_id=? ORDER BY blog.bloguser_id DESC";
		Connection con=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		try {
			con=DBtools.getConnection();
			pre=con.prepareStatement(str);
			pre.setInt(1, bloguser_id);
			rs=pre.executeQuery();
			while(rs.next())
			{
				BlogUi blog=new BlogUi();
				blog.blog_id=rs.getInt(1);
				blog.blog_date=rs.getString(2);
				blog.blog_content=rs.getString(3);
				blog.bloguser_id=rs.getInt(4);
				blog.blogstate=rs.getString(5);
				blog.blogtype=rs.getString(6);
				blog.setBloguser_nickname(rs.getString(7));
				List<ImageFile> list1=findImage(blog.blog_id);
				blog.setList(list1);
				list.add(blog);
				
			}
			rs.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(con);
		}
		
		
		return list;
		
	}
	public List<BlogUi> findCateBlog(String type)
	{
		List<BlogUi> list=new ArrayList<>();
		String str="SELECT blog.blog_id,blog.blog_date,blog.blog_content,blog.bloguser_id,blog.blogstate,blog.blogtype,bloguser.bloguser_nickname FROM blog ,bloguser WHERE bloguser.bloguser_id=blog.bloguser_id AND blog.blogtype=?";
		Connection con=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		try {
			con=DBtools.getConnection();
			pre=con.prepareStatement(str);
			pre.setString(1, type);
			rs=pre.executeQuery();
			while(rs.next())
			{
				BlogUi blog=new BlogUi();
				blog.blog_id=rs.getInt(1);
				blog.blog_date=rs.getString(2);
				blog.blog_content=rs.getString(3);
				blog.bloguser_id=rs.getInt(4);
				blog.blogstate=rs.getString(5);
				blog.blogtype=rs.getString(6);
				blog.setBloguser_nickname(rs.getString(7));
				List<ImageFile> list1=findImage(blog.blog_id);
				blog.setList(list1);
				list.add(blog);
				
			}
			rs.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(con);
			
		}
		
		
		return list;
	}

	
	public List<BlogUi> findAllBlogUi(int page)
	{
		List<BlogUi> list=new ArrayList<>();
		int page1=(page-1)*10;
		String sql="SELECT blog.blog_id,blog.blog_date,blog.blog_content,blog.bloguser_id,blog.blogstate,blog.blogtype,bloguser.bloguser_nickname FROM blog ,bloguser WHERE (bloguser.bloguser_id=blog.bloguser_id AND blogstate!='Œ¥…Û∫À') ORDER BY blog_id DESC limit 10 offset ?";
		Connection con=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		try {
			con=DBtools.getConnection();
			pre=con.prepareStatement(sql);
			pre.setInt(1, page1);
			rs=pre.executeQuery();
			while(rs.next())
			{
				BlogUi blog=new BlogUi();
				blog.blog_id=rs.getInt(1);
				blog.blog_date=rs.getString(2);
				blog.blog_content=rs.getString(3);
				blog.bloguser_id=rs.getInt(4);
				blog.blogstate=rs.getString(5);
				blog.blogtype=rs.getString(6);
				blog.setBloguser_nickname(rs.getString(7));
				List<ImageFile> list1=findImage(blog.blog_id);
				blog.setList(list1);
				list.add(blog);
				
			}
			rs.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(con);
		}
		
		
		return list;
		
	}
	public List<BlogUi> findNoCheckBlogUi(int page)
	{
		List<BlogUi> list=new ArrayList<>();
		int page1=(page-1)*10;
		String sql="SELECT blog.blog_id,blog.blog_date,blog.blog_content,blog.bloguser_id,blog.blogstate,blog.blogtype,bloguser.bloguser_id,bloguser.bloguser_nickname FROM blog ,bloguser WHERE (bloguser.bloguser_id=blog.bloguser_id AND blogstate='Œ¥…Û∫À') ORDER BY blog_id DESC  limit 10 offset ?";
		Connection con=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		try {
			con=DBtools.getConnection();
			pre=con.prepareStatement(sql);
			pre.setInt(1, page1);
			rs=pre.executeQuery();
			while(rs.next())
			{
				BlogUi blog=new BlogUi();
				blog.blog_id=rs.getInt(1);
				blog.blog_date=rs.getString(2);
				blog.blog_content=rs.getString(3);
				blog.bloguser_id=rs.getInt(4);
				blog.blogstate=rs.getString(5);
				blog.blogtype=rs.getString(6);
				blog.setBloguser_nickname(rs.getString(7));
				List<ImageFile> list1=findImage(blog.blog_id);
				blog.setList(list1);
				list.add(blog);
				
			}
			rs.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(con);
		}
		
		
		return list;
		
	}
	public List<ImageFile> findImage(int blogId)
	{
		List<ImageFile> list=new ArrayList<>();
		String sql="SELECT file.file_id,file.file_address,file.blog_id FROM file WHERE blog_id = ?";
		Connection con=null;
		PreparedStatement pre=null;
		ResultSet rs=null;
		try {
			con=DBtools.getConnection();
			pre=con.prepareStatement(sql);
			pre.setInt(1, blogId);
			rs=pre.executeQuery();
			while(rs.next())
			{
				ImageFile image=new ImageFile();
				image.setBlog_id(rs.getInt(3));
				image.setFile_address(rs.getString(2));
				image.setFile_id(rs.getInt(1));
				list.add(image);
			}
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally{
			DbUtils.closeQuietly(con);
		}
		return list;
		
	}

}
