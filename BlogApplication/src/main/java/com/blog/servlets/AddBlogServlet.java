package com.blog.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.print.event.PrintJobAttributeEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.blog.dao.PostDao;
import com.blog.entities.Blogs;
import com.blog.entities.User;
import com.blog.helper.ConnectionProvider;
import com.blog.helper.Helper;

/**
 * Servlet implementation class AddBlogServlet
 */
@MultipartConfig
@WebServlet("/AddBlogServlet")
public class AddBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBlogServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter outWriter = response.getWriter();
//		outWriter.println("onn add blog");
		String title = request.getParameter("blog-title");
		String content = request.getParameter("blog-content");
		int catId = Integer.parseInt(request.getParameter("catId"));
		System.out.println(catId);
		Part pimage = request.getPart("blog-image");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("currentUser");
		System.out.println(user.getId());
		System.out.println(user.getName());
//		outWriter.println(title);
//		outWriter.println(content);
//		outWriter.println(catId);
//		outWriter.println(pimage.getSubmittedFileName());
//		outWriter.println(user.getName());
		try {
			Blogs blog = new Blogs(title,content, catId, user.getId(), pimage.getSubmittedFileName());
			PostDao dao = new PostDao(ConnectionProvider.getConnect());
			boolean status=   dao.addBlog(blog);
			String path = request.getRealPath("/")+"postImages" +File.separator +blog.getpImage();
			System.out.println(path);
			if (status) {
				outWriter.println("done");
				Helper.saveFile(pimage.getInputStream(), path);
				
			} else {

				outWriter.println("something went wrong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

}
