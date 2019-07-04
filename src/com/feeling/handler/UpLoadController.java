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
		} // ���ñ���

		// ��ô����ļ���Ŀ����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// ��ȡ�ļ���Ҫ�ϴ�����·��
		String path = request.getSession().getServletContext().getRealPath("/blogimages");

		// ���û�����������õĻ����ϴ���� �ļ� ��ռ�� �ܶ��ڴ棬
		// ������ʱ��ŵ� �洢�� , ����洢�ң����Ժ� ���մ洢�ļ� ��Ŀ¼��ͬ
		/**
		 * ԭ�� �����ȴ浽 ��ʱ�洢�ң�Ȼ��������д�� ��ӦĿ¼��Ӳ���ϣ� ������˵ ���ϴ�һ���ļ�ʱ����ʵ���ϴ������ݣ���һ������ .tem
		 * ��ʽ�� Ȼ���ٽ�������д�� ��ӦĿ¼��Ӳ����
		 */
		factory.setRepository(new File(path));
		// ���� ����Ĵ�С�����ϴ��ļ������������û���ʱ��ֱ�ӷŵ� ��ʱ�洢��
		factory.setSizeThreshold(1024 * 1024);

		// ��ˮƽ��API�ļ��ϴ�����
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			
			String blogcontent = null, userid = null;
			// �����ϴ�����ļ�
			int blogid=(int) (System.currentTimeMillis()+i);
			i++;
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			List<ImageFile> list1 = new ArrayList<>();
			for (FileItem item : list) {
				// ��ȡ������������
				String name = item.getName();

				// �����ȡ�� ����Ϣ����ͨ�� �ı� ��Ϣ
				if (item.isFormField()) {
					// ��ȡ�û�����������ַ��� ���������ͦ�ã���Ϊ���ύ�������� �ַ������͵�
					String value = item.getString();
					if (item.getFieldName().equals("blog_content")) {
						blogcontent = item.getString();

					} else if (item.getFieldName().equals("blog_userId")) {
						userid = item.getString();
						

					} 
					System.out.println("value" + item.getFieldName());
					System.out.println("blog_content" + value);

				}
				// �Դ���ķ� �򵥵��ַ������д��� ������˵�����Ƶ� ͼƬ����Ӱ��Щ
				else {
					/**
					 * ������������Ҫ��ȡ �ϴ��ļ�������
					 */
					// ��ȡ·����
					String value = item.getName();
					// ���������һ����б��
					int start = value.lastIndexOf("\\");
					// ��ȡ �ϴ��ļ��� �ַ������֣���1�� ȥ����б�ܣ�
					String filename = value.substring(start + 1);
                    System.out.println(filename);
                    System.out.println(path+filename);
                    item.write(new File(path, filename));
					ImageFile img = new ImageFile();
					img.setBlog_id(blogid);
					img.setFile_address(URL.URL1 + filename);
					
					// �������ṩ��
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
