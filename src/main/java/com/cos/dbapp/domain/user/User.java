package com.cos.dbapp.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cos.dbapp.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
// 그 세상에 맞게 변화하는것 modeling
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // 프라이머리키 (기본키)
	
	@Column(unique = true, length = 20)
	private String username;
	private String password;
	private String email;
	private String address;
	
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address +  "]";
	}
	//Foreign키가 만들어질수없다. ManyToOne , OneToMany (양방향 Mapping) 조인을 알아서해준다.
	// 걸어줘야하는 이유 밑에 설명 
	@JsonIgnoreProperties({"user","title"}) // @JsonIgnoreProperties이것을 쓰는 이유는 OneToMany의 무한반복을막아준다. 뺴고싶은부분은 ({})의부분에 넣어준다.
	@OneToMany(mappedBy = "user") // 1. mappedBy = "user" -> 이 의미는 User 클래스의 변수가현재 user user는 Foreign키를 만들지말라라는 의미
	private List<Post>posts;				//
	
	public List<Post> getPosts() {
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
