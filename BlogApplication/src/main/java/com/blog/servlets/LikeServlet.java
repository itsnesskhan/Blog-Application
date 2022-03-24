package com.blog.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.undo.AbstractUndoableEdit;

import com.blog.dao.LikedDao;
import com.blog.helper.ConnectionProvider;

/**
 * Servlet implementation class LikeServlet
 */
@WebServlet("/LikeServlet")
public class LikeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int pid= Integer.parseInt(request.getParameter("blog"));
		int uid= Integer.parseInt(request.getParameter("user"));
		String operation= request.getParameter("operation");
		out.print(pid);
		out.print(uid);
		out.println(operation);
		LikedDao dao = new LikedDao(ConnectionProvider.getConnect());
		if (operation.equals("like")) {
			dao.insertLiked(pid, uid);
			
		}
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		int pid= Integer.parseInt(request.getParameter("blog"));
		out.print(pid);
	}

}
