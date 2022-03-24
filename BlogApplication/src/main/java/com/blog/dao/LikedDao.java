package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.blog.entities.Blogs;

public class LikedDao {
	Connection con;

	public LikedDao(Connection con) {
		super();
		this.con = con;
	}
	
	public boolean insertLiked(int postId, int userid) {
		boolean f= false;
		try {
			String qString = "insert into liked (postid, uid) values(?,?)";
			PreparedStatement psmt = this.con.prepareStatement(qString);
			System.out.println(postId);
			System.out.println(userid);
			psmt.setInt(1, postId);
			psmt.setInt(2, userid);
			psmt.executeUpdate();
			f= true;
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public int countLikesOnPost(int postId) {
		int count =0;
		try {
			String qString ="select count(*) from liked where postid = ?";
			PreparedStatement psmt = this.con.prepareStatement(qString);
			psmt.setInt(1, postId);
			ResultSet set = psmt.executeQuery();
			if (set.next()) {
				count = set.getInt("count(*)");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public boolean isLikedbyUser(int postid, int userid) {
		boolean f= false;
		try {
			String qString = "select * from liked where postid= ? and uid= ?";
			PreparedStatement psmt = this.con.prepareStatement(qString);
			psmt.setInt(1, postid);
			psmt.setInt(2, userid);
			ResultSet set= psmt.executeQuery();
			while (set.next()) {
				f= true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public boolean deleteLike(int postid, int userid) {
		boolean f= false;
		try {
			PreparedStatement psmt = this.con.prepareStatement("delete from liked where postid =? and uid= ?");
			psmt.setInt(1, postid);
			psmt.setInt(2, userid);
			psmt.executeUpdate();
			f= true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
}
