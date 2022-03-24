package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.blog.entities.Blogs;
import com.blog.entities.Categories;

public class PostDao {
	private Connection con;

	public PostDao(Connection connection) {
		super();
		this.con = connection;
	}
	
	public boolean addBlog(Blogs blog) {
		boolean f = false;
		try {
			String qString ="insert into blogs(title, content, catid, userid, pimage) values(?,?,?,?,?)";
			PreparedStatement psmt = this.con.prepareStatement(qString);
			psmt.setString(1, blog.getTitle() );
			psmt.setString(2, blog.getContent());
			psmt.setInt(3, blog.getCatId());
			psmt.setInt(4, blog.getUserId());
			psmt.setString(5, blog.getpImage());
			
			psmt.executeUpdate();
			f= true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	
	
	public ArrayList<Categories> getAllCategories() {
		ArrayList<Categories> list = new ArrayList<Categories>();
		
		try {
			String qString = "select * from categories";
			Statement stmt =  this.con.createStatement();
			ResultSet set =  stmt.executeQuery(qString);
			
			while (set.next()) {
				int catId = set.getInt("id");
				String catName = set.getString("name");
				
				Categories categories = new Categories(catId, catName);
				list.add(categories);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	public List<Blogs> getAllBlogs() {
		List<Blogs> list =  new ArrayList<Blogs>();
		  try {
			  String qString ="select * from blogs order by id DESC";
			  Statement stmt = this.con.createStatement();
			  ResultSet set = stmt.executeQuery(qString);
			  
			  while (set.next()) {
				Blogs blogs = new Blogs();
				blogs.setId(set.getInt("id"));
				blogs.setTitle(set.getString("title"));
				blogs.setContent(set.getString("content"));
				blogs.setUserId(set.getInt("userid"));
				blogs.setCatId(set.getInt("catid"));
				blogs.setpImage(set.getString("pimage"));
				blogs.setPdate(set.getTimestamp("pdate"));
				
				list.add(blogs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		return list;
	}
	
	
	public List<Blogs> getBlogsByCatId(int catId) {
		List<Blogs> list =  new ArrayList<Blogs>();
		  try {
			  String qString ="select * from blogs where catid=?";
			  PreparedStatement psmt = this.con.prepareStatement(qString);
			  psmt.setInt(1, catId);
			  ResultSet set=  psmt.executeQuery();
			  
			  while (set.next()) {
				Blogs blogs = new Blogs();
				blogs.setId(set.getInt("id"));
				blogs.setTitle(set.getString("title"));
				blogs.setContent(set.getString("content"));
				blogs.setUserId(set.getInt("userid"));
				blogs.setpImage(set.getString("pimage"));
				blogs.setPdate(set.getTimestamp("pdate"));
				
				list.add(blogs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		return list;
	}
	
	
	public Blogs getBlogbyId(int bid){
		Blogs blogs = new Blogs();
		try {
			String qString = "select * from blogs where id=?";
			PreparedStatement psmt = this.con.prepareStatement(qString);
			psmt.setInt(1, bid);
			ResultSet set= psmt.executeQuery();
			
			while (set.next()) {
				blogs.setId(set.getInt("id"));
				blogs.setTitle(set.getString("title"));
				blogs.setContent(set.getString("content"));
				blogs.setUserId(set.getInt("userid"));
				blogs.setpImage(set.getString("pimage"));
				blogs.setCatId(set.getInt("catid"));
				blogs.setPdate(set.getTimestamp("pdate"));
			
				} 	
			System.out.println(blogs);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return blogs;
	}
	
}
