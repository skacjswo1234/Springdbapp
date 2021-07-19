package com.cos.dbapp.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.dbapp.domain.comment.Comment;
import com.cos.dbapp.domain.comment.CommentRepository;
import com.cos.dbapp.domain.post.Post;
import com.cos.dbapp.domain.post.PostRepository;
import com.cos.dbapp.domain.user.User;
import com.cos.dbapp.util.Script;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 붙어있는 객체들을 자동으로 의존성주입을 해준다. 생성자를 통해서 주입하는것을 대신역할해준다.
@Controller
public class postController {

	private final PostRepository postRepository;
	private final HttpSession session;
	private final HttpServletRequest request;
	private final CommentRepository commentRepository;
	

	@GetMapping({ "/", "/post" })
	public String list(Model model) { // model = request (Spring 지원)

		// postEntity는 db에서 받아올 데이터의미.
		model.addAttribute("postsEntity", postRepository.findAll());
		return "post/list"; // ViewResolver도움 !! + RequestDispatcher (request유지 기법)
	}

	@GetMapping("/post/{id}")
	public String detail(@PathVariable int id, Model model) {
		System.out.println("받은 아이디 : "+id);
		Post postEntity = postRepository.findById(id).get();
		model.addAttribute("postEntity", postEntity);
		
		List<Comment> commentsEntity = commentRepository.mFindAllByPostId(id);
		model.addAttribute("commentsEntity",commentsEntity);
		
		return "post/detail";

	}

	@DeleteMapping("/post/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) { // ResponseBody - MessageConvert를 타게되어있다.
																	// (ViewResolver를 타지않는다.)
		// 1.권한체크(post id를 통해서 user id를 찾아와서 session의 user id를 비교) - 생략

		// 세션의 user id 찾기 (session)
		User principal = (User) session.getAttribute("principal");
		int userId = principal.getId();

		// int userId = ((User) session.getAttribute("principal")).getId();
		// post의 user id 찾기(id)
		Post postEntity = postRepository.findById(id).get(); // 문제점발생 처리해야함.
		int postUserId = postEntity.getUser().getId();

		// 2.{id}값으로 삭제
		if (userId == postUserId) {
			postRepository.deleteById(id);
			return "ok";
		} else {
			return "fail";
		}

	} // end of deleteById

	@GetMapping("/post/saveForm")
	public String saveForm() {
		// 1.인증 체크
		return "post/saveForm";
	}

	@PostMapping("/post")
	public String save(Post post) {
		User principal = (User) session.getAttribute("principal");
		// User u = new User();
		// u.setId(1);
		if (principal == null) {
			return "redirect:/auth/loginForm";
		}

		// post.setUser(u);
		post.setUser(principal);
		postRepository.save(post);
		return "redirect:/";
	}

	@GetMapping("/post/{id}/updateForm")
	public String updateForm(@PathVariable int id, Model model) {
		User principal = (User) session.getAttribute("principal");
		int loginId = principal.getId();

		Post postEntity = postRepository.findById(id).get();
		int postOwnerId = postEntity.getUser().getId();

		if (loginId == postOwnerId) {
			model.addAttribute("postEntity", postEntity); // 게시글을 쓰는 1명이기때문에 1명걸 조인하는게 좋다.

			return "post/updateForm";
		} else {
			return "redirect:/auth/loginForm";
			// Post postEntity = postRepository.findById(id).get();
			// return"post/updateForm";
		}
	}

	@PutMapping("post/{id}")
	public @ResponseBody String updateForm(@PathVariable int id, @RequestBody Post post) {
		Post postEntity = postRepository.findById(id).get();
		postEntity.setTitle(post.getTitle());
		postEntity.setContent(post.getContent());
		postRepository.save(postEntity);
		return "ok";

	}
}
