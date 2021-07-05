package com.cos.dbapp.test;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cos.dbapp.domain.post.Post;
import com.cos.dbapp.domain.post.PostRepository;

@RestController
public class PostApiControllerTest {

	private final PostRepository postRepository;

	public PostApiControllerTest(PostRepository postRepository) {
		this.postRepository = postRepository;
	
	}
	
	@GetMapping("/test/post")
	public List<Post>findAll(){
		// SELECT * FROM post
		return postRepository.findAll();
	}
		
	@GetMapping("/test/post/{id}")
	public String findById(@PathVariable int id) {
		Post postEntity = postRepository.findById(id).get();
		return "ok";
	}
	
	
}
