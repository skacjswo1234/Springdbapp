package com.cos.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.dbapp.domain.post.Post;
import com.cos.dbapp.domain.post.PostRepository;
import com.cos.dbapp.domain.user.User;
import com.cos.dbapp.util.Script;

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
	public String list(Model model) { // model = request (Spring 지원)
		
		// postEntity는 db에서 받아올 데이터의미.
		model.addAttribute("postsEntity",postRepository.findAll()); 
		return "post/list"; // ViewResolver도움 !! + RequestDispatcher (request유지 기법)
	}
	
	@GetMapping("/post/{id}")
		public String detail(@PathVariable int id, Model model) {
			Post postEntity = postRepository.findById(id).get();			
			model.addAttribute("postEntity",postEntity);
			return "post/detail";
			
	}
	
	@PostMapping("/post/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) { //ResponseBody - MessageConvert를 타게되어있다. (ViewResolver를 타지않는다.)
		// 1.권한체크(post id를 통해서 user id를 찾아와서 session의 user id를 비교) - 생략
		
		
		// 세션의 user id 찾기 (session)
		User principal = (User) session.getAttribute("principal");
		int userId = principal.getId();
		
		//int userId = ((User) session.getAttribute("principal")).getId();
		// post의 user id 찾기(id)
		Post postEntity = postRepository.findById(id).get(); // 문제점발생 처리해야함.
		int postUserId = postEntity.getUser().getId();
		
		// 2.{id}값으로 삭제
		if(userId == postUserId) {
			postRepository.deleteById(id);
			return Script.href("/", "삭제완료");
		} else {
			return Script.back("삭제 실패");
		}
		
	} // end of deleteById	
	
	@GetMapping("/post/saveForm")
	public String saveForm(String title, String content, Model model) {
		// 1.인증 체크 - 숙제 
		return "post/saveForm";
		
	}
	
}













