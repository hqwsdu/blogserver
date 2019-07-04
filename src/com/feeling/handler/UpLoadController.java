package com.feeling.handler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.http.server.ServletServerHttpAsyncRequestControl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feeling.bean.Blog;
import com.feeling.bean.ImageFile;
import com.feeling.dao.ImageFileDao;
import com.feeling.service.BlogService;
import com.feeling.tools.URL;

@Controller
public class UpLoadController {
	private int i=0;
	

	@ResponseBody
	@RequestMapping(value = "/ContentUp", method = RequestMethod.GET)
	public String ContentUp(HttpServletRequest request, HttpSession session) {
		String result = "error";
	
		return result;

	}

	@ResponseBody
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
	public String fileUpload(HttpServletRequest request, HttpSession session) {
		String result = "error";
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {

			e1.printStackTrace();
		} // 设置编码

		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 获取文件需要上传到的路径
		String path = request.getSession().getServletContext().getRealPath("/blogimages");

		// 如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
		// 设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(path));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(1024 * 1024);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			
			String blogcontent = null, userid = null;
			// 可以上传多个文件
			int blogid=(int) (System.currentTimeMillis()+i);
			i++;
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			List<ImageFile> list1 = new ArrayList<>();
			for (FileItem item : list) {
				// 获取表单的属性名字
				String name = item.getName();

				// 如果获取的 表单信息是普通的 文本 信息
				if (item.isFormField()) {
					// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					String value = item.getString();
					if (item.getFieldName().equals("blog_content")) {
						blogcontent = item.getString();

					} else if (item.getFieldName().equals("blog_userId")) {
						userid = item.getString();
						

					} 
					System.out.println("value" + item.getFieldName());
					System.out.println("blog_content" + value);

				}
				// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else {
					/**
					 * 以下三步，主要获取 上传文件的名字
					 */
					// 获取路径名
					String value = item.getName();
					// 索引到最后一个反斜杠
					int start = value.lastIndexOf("\\");
					// 截取 上传文件的 字符串名字，加1是 去掉反斜杠，
					String filename = value.substring(start + 1);
                    System.out.println(filename);
                    System.out.println(path+filename);
                    item.write(new File(path, filename));
					ImageFile img = new ImageFile();
					img.setBlog_id(blogid);
					img.setFile_address(URL.URL1 + filename);
					
					// 第三方提供的
				    System.out.println("imagefile"+img.toString());
					ImageFileDao dao = new ImageFileDao();
					dao.addImageFile(img);

				}

			}
			Blog blog = new Blog();
			blog.blog_content = blogcontent;
			blog.bloguser_id = Integer.parseInt(userid);
			blog.blog_id=blogid;
			System.out.println(blog);
			BlogService bs = new BlogService();

			result = bs.addBlog(blog);

		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block

			// e.printStackTrace();
		}

		return result;
	}

}
