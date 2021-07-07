package com.cos.dbapp.web;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		@GetMapping("/user/updateForm")
		public String updateForm() {
			// 1. 인증과 권한을 검사해야함
			// 2. 세션값 사용하면 됨.
			return "user/updateForm";
		}
	
		@PostMapping("/user/{id}") // 원래는 put으로 해야한다. 나중에 자바스크립트로 put 요청하기!!
		public String update(@PathVariable int id, String password, String address) {
			
			// 공통관심사 (AOP 공통관심사 분리) 스프링 AOP (관점지향프로그램 : Aspect Oriented Programming)
			User principal = (User) session.getAttribute("principal");
		
			if(principal != (User) session.getAttribute("principal"));
			
			if(principal != null && id == principal.getId()) {
				User userEntity = userRepository.findById(id).get();
				userEntity.setPassword(password);
				userEntity.setAddress(address);
				userRepository.save(userEntity); 
				session.setAttribute("principal", userEntity); // principal 해시맵이다 값에는 키가필요하다.
				return "redirect:/";
			}
			return "redirect:/auth/loginForm";
		}

		@GetMapping("/juso")
		public String jusoRequest() {	
			return "juso/jusoPopup";
		}
		@PostMapping("/juso")
		public String jusoResponse(String roadFullAddr, String inputYn, Model model) {	
			System.out.println("주소 :" + roadFullAddr);
			model.addAttribute("roadFullAddr", roadFullAddr);
			model.addAttribute("inputYn",inputYn);
			return "juso/jusoPopup";
		}
}





