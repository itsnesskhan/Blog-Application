package com.blog.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.blog.dao.UserDao;
import com.blog.entities.Message;
import com.blog.entities.User;
import com.blog.helper.ConnectionProvider;
import com.blog.helper.Helper;

/**
 * Servlet implementation class EditServlet
 */
@MultipartConfig
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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
		PrintWriter out = response.getWriter();
		String name = request.getParameter("user-name");
		String email = request.getParameter("user-email");
		String password = request.getParameter("user-password");
		Part part = request.getPart("user-profile");
		String profile = part.getSubmittedFileName();
		
		try {
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("currentUser");
			user.setName(name);
			user.setEmail(email);
			user.setPassword(password);
			String oldprofile = user.getProfile();
			System.out.println("olldprof"+oldprofile);
			if (profile==null) {
				profile="default.jpg";
			}
			user.setProfile(profile);
			
			UserDao dao = new UserDao(ConnectionProvider.getConnect());
			boolean status = dao.updateUser(user);
			String oldpath= request.getRealPath("/")+"pics"+File.separator+oldprofile;
			String path = request.getRealPath("/")+"pics"+File.separator+user.getProfile();
			if (status) {
//				if (!oldprofile.equals("default.jpg")) {
//					System.out.println("inside");
//					Helper.deleteFile(oldpath);
//				}
				//Helper.deleteFile(path);
				if (Helper.saveFile(part.getInputStream(), path)) {
					Message message = new Message("User Details Updated Successfully!", "success", "alert-success");
					session.setAttribute("msg", message);	
					
				}else {
					Message message = new Message("Something went wrong!", "error", "alert-danger");
					session.setAttribute("msg", message);
				} 
				
			}
			else {
				Message message = new Message("Something went wrong!", "error", "alert-danger");
				session.setAttribute("msg", message);
			}
			response.sendRedirect("profile.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
