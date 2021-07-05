package com.cos.dbapp.domain.post;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.cos.dbapp.domain.user.User;


@Entity
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 프라이머리키 (기본키)
	private String title; // varchar(255), 255는 글자수 //char 고정 varchar 가변
	@Lob // Long text의 어노테이션이다. 큰글자수를 넣을수있다. (jpa문법)
	private String content;
	
	// jpa (java Persistence API\
	@JoinColumn(name = "user_id") // 조인의 Foreign키의 이름을 바꾸는 어노테이션이다.(잡기술)
	@ManyToOne(fetch = FetchType.EAGER) //Foreign 키를 만드는 어노테이션이다 JPA기술 -> hibernate -> ORM 
	private User user;  // ORM 사용
	
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", user=" + user + "]";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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
	
	
}
