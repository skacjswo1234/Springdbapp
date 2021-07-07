package com.cos.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cos.dbapp.domain.post.PostRepository;

@Controller
public class postController {

	private final PostRepository postRepository;
	private final HttpSession session;

	// dependency 의존성 이어준다.
	public postController(PostRepository postRepository, HttpSession session) {
		this.postRepository = postRepository;
		this.session = session;
	}




	@GetMapping({"/", "/post"})
	public String list(Model model) {
		
		// postEntity는 db에서 받아올 데이터의미.
		model.addAttribute("postsEntity",postRepository.findAll()); 
		return "post/list";
	}
}
