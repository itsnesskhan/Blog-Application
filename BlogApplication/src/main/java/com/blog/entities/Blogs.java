package com.blog.entities;

import java.sql.Timestamp;

public class Blogs {
	private int id;
	private String title;
	private String content;
	private int userId;
	private int catId;
	private Timestamp pdate;
	private String pImage;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getCatId() {
		return catId;
	}


	public void setCatId(int catId) {
		this.catId = catId;
	}


	public Timestamp getPdate() {
		return pdate;
	}


	public void setPdate(Timestamp pdate) {
		this.pdate = pdate;
	}


	public Blogs(int id, String title, String content, int catId,int userId, String pImage) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.catId = catId;
		this.pImage = pImage;
	}


	public Blogs(String title, String content,int catId, int userId, String pImage) {
		super();
		this.title = title;
		this.content = content;
		this.userId = userId;
		this.catId = catId;
		this.pImage = pImage;
	}


	public Blogs() {
		super();
	}


	public String getpImage() {
		return pImage;
	}


	public void setpImage(String pImage) {
		this.pImage = pImage;
	}


	@Override
	public String toString() {
		return "Blogs [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId + ", catId="
				+ catId + ", pdate=" + pdate + ", pImage=" + pImage + "]";
	}
	
	
	
	
	
	
}
