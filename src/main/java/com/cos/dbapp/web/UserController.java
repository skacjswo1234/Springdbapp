package com.cos.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cos.dbapp.domain.user.User;
import com.cos.dbapp.domain.user.UserRepository;
import com.cos.dbapp.util.Script;

@Controller
public class UserController {
	
	private final UserRepository userRepository;
	private final HttpSession session;
	
	
	
	public UserController(UserRepository userRepository, HttpSession session) {
		this.userRepository = userRepository;
		this.session = session;
	}

	@GetMapping("/auth/joinForm") 
	// auth 풀더는 인증이필요없는 폴더로 지정 할 예정
	public String joinForm() {
		return "auth/joinForm";
	}
	
	@GetMapping("/auth/loginForm") 
	// auth 풀더는 인증이필요없는 폴더로 지정 할 예정
	public String loginForm() {
		return "auth/loginForm";
	}

	@PostMapping("/auth/join")
	public  String join(User user) {
		userRepository.save(user);
		return "redirect:/auth/loginForm"; // redirect는 데이터를 가지고 그담페이지로 이동한다.
	}
	//RestController 데이터를 리턴한다.(ResponseBody)
	@PostMapping("/auth/login")
	public @ResponseBody String login(User user) {
		User userEntity = userRepository.mLogin(user.getUsername(),user.getPassword());
		if(userEntity ==null) {
	
			return Script.back("로그인실패");
		} else {
			session.setAttribute("principal", userEntity); //principal 인증주체
			return Script.href("/");
		}
	}
		
		@GetMapping("/user/logout")
		public String logout() {
			session.invalidate();
			return "redirect:/";
		}
	
}





