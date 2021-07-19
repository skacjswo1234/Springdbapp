package com.cos.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.dbapp.domain.comment.Comment;
import com.cos.dbapp.domain.comment.CommentRepository;
import com.cos.dbapp.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentController {

	private final CommentRepository commentRepository;
	private final HttpSession session;
	
	// 1. save -Post - Data Return(responseBody)
	
	// 2. delete - Delete - ajax(비동기함수fetch)
	// 3. 안드로이드로 통신할때는 api로 만들어서 통신해야한다.
	@DeleteMapping("/comment/{id}")
	public @ResponseBody String deleteById(@PathVariable int id) {
		User principal = (User) session.getAttribute("principal");
		int userId = principal.getId();
		
		Comment commentEntity = commentRepository.findById(id).get();
		int commentUserId = commentEntity.getUser().getId();
		
		if(userId == commentUserId) {
			commentRepository.deleteById(id);
			return "ok";
		} else {
			return "fail";
		}	
	}
	
}
