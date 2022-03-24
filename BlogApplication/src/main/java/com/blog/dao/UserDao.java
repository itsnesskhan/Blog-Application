package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.blog.entities.User;

public class UserDao {
	private  Connection con;

	public UserDao(Connection con) {
		this.con = con;
	}
	
	public boolean saveUser(User user) {
		boolean f= false;
		try {
			String qString = "insert into users (name, password, email,gender) values(?,?,?,?)";
			PreparedStatement psmt = this.con.prepareStatement(qString);
			psmt.setString(1, user.getName());
			psmt.setString(2, user.getPassword());
			psmt.setString(3, user.getEmail());
			psmt.setString(4, user.getGender());
			
			psmt.executeUpdate();
			f= true;
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return f;
	}
	
	public User getUserByEmail(String email,String password) {
		User user = null;
		try {
			String query = "select * from users where email =? and password = ?";
			PreparedStatement psmt = this.con.prepareStatement(query);
			psmt.setString(1, email);
			psmt.setString(2, password);
			ResultSet set = psmt.executeQuery();
			
			if (set.next()) {
				user = new User();
				String name = set.getString("name");
				user.setName(name);
				user.setId(set.getInt("id"));
				user.setEmail(set.getString("email"));
				user.setPassword(set.getString("password"));
				user.setGender(set.getString("gender"));
				Timestamp dateTimestamp = set.getTimestamp("rdate");
				user.setDateTime(dateTimestamp);
				user.setProfile(set.getString("profile"));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	public boolean updateUser(User user) {
		boolean f = false;
			try {
				
				String queString = "update users set name=?, email=?, gender=?, password=?, profile=? where id=?";
				PreparedStatement psmt = this.con.prepareStatement(queString);
				psmt.setString(1, user.getName());
				psmt.setString(2, user.getEmail());
				psmt.setString(3, user.getGender());
				psmt.setString(4, user.getPassword());
				psmt.setString(5, user.getProfile());
				psmt.setInt(6, user.getId());
				
				psmt.executeUpdate();
				f= true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return f;
	}
	
	
}
