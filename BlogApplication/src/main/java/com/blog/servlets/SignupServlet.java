package com.blog.servlets;
import com.blog.dao.UserDao;
import com.blog.entities.User;
import com.blog.helper.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Executable;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SignupServlet
 */
@MultipartConfig
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignupServlet() {
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
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		String checkbox = request.getParameter("checkbox");
		
		
		
		try {
			if (checkbox == null) {
				out.println("Please Agree to the tearms and Conditions");
			}
			else if (name.isEmpty()) {
				out.println("Please enter your name");
			}
			else if (email.isEmpty()) {
				out.println("Please enter your email");
			}
			else if (password.isBlank()) {
				out.println("Please enter your password");
			}
			else if (password.length()<8) {
				out.println("Password must be 8 character long");	
			}
			else {
				//creating user object
				User user = new User(name, email, password, gender);
				System.out.println(user);
				UserDao dao = new UserDao(ConnectionProvider.getConnect());
				if (dao.saveUser(user)) {
					out.println("done");
				}else {
					out.print("Error");
				}
				
			}
			
			
		} catch (Exception e) {
			out.println(e);
			// TODO: handle exception
		}
	}
 
}
