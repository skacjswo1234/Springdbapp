package com.cos.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.dbapp.domain.comment.Comment;
import com.cos.dbapp.domain.comment.CommentRepository;
import com.cos.dbapp.domain.dto.CMRespDto;
import com.cos.dbapp.domain.dto.CommentSaveReqDto;
import com.cos.dbapp.domain.post.Post;
import com.cos.dbapp.domain.post.PostRepository;
import com.cos.dbapp.domain.user.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class CommentController {

	private final CommentRepository commentRepository;
	private final HttpSession session;
	private final PostRepository postRepository;
	
	// 1. save -Post - Data Return(responseBody)
	// 데이터를 받을때는 무조건 Dto를 써야한다 리플렉션(replaction)
	@PostMapping("/comment")
	public @ResponseBody CMRespDto<Comment>save(@RequestBody CommentSaveReqDto dto) {
		
		Comment comment = new Comment();
		comment.setText(dto.getText());
		
		Post postEntity = postRepository.findById(dto.getPostId()).get();
		postEntity.setId(dto.getPostId());
		comment.setPost(postEntity);
		
		User principal = (User) session.getAttribute("principal");
		comment.setUser(principal);
		
		Comment commentEntity = commentRepository.save(comment);
		return new CMRespDto<>(1,"댓글쓰기성공",commentEntity); 
	}
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
