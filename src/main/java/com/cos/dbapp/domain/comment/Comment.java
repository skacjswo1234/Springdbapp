package com.cos.dbapp.domain.comment;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cos.dbapp.domain.post.Post;
import com.cos.dbapp.domain.user.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data // Getter, Setter, ToString을 만들어준다.
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 데이터를 넣을때 인덱스를 만들어서 넣어놓으면 빨리찾을수있다.
	private int id; // 프라이머리키 (기본키)
	private String text; // 댓글 내용
	
	@JsonIgnoreProperties({"posts"}) // posts부분은 json으로 파싱하지 않게다는 말이다.
	@JoinColumn(name = "user_id") // Foreign키 이름
	@ManyToOne()
	private User user;
	
	@JsonIgnoreProperties({"user"}) // users부분은 json으로 파싱하지 않겠다는 말이다.
	@JoinColumn(name = "post_id") // Foreign키 이름
	@ManyToOne()
	private Post post;
	
}
