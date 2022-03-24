package com.blog.entities;

public class Liked {
	private int id;
	private int postid;
	private int uid;
	
	public Liked(int id, int postid, int uid) {
		super();
		this.id = id;
		this.postid = postid;
		this.uid = uid;
	}

	public Liked(int postid, int uid) {
		super();
		this.postid = postid;
		this.uid = uid;
	}

	public Liked() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}
	
	
	
}
