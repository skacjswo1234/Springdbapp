package com.cos.dbapp.test;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.dbapp.domain.user.User;
import com.cos.dbapp.domain.user.UserRepository;

@RestController
public class UserApiControllerTest {
	
	private final UserRepository userRepository;

	// 의존성 주입 (DI)
	public UserApiControllerTest(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@PostMapping("/test/user")
	public String save(User user) {
		userRepository.save(user);
		return "save ok";
	}
	
	@GetMapping("/test/user")
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	
	// http://localhost:8000/user/2
	// 유저들을 정보를 하나씩 찾는 방법.
	@GetMapping("/test/user/{id}") //PathValue 방식을 요즘은 많이 선호한다.
	public User findById(@PathVariable int id) {
		return userRepository.findById(id).get(); 
	}
	// username만 찾는 방법.
	@GetMapping("/test/user/username/{username}")
	public User findByUsername(@PathVariable String username) {
		return userRepository.mFindByUsername(username);
	}
	
	@PostMapping("/test/login")
	public String login(@RequestBody User user) {
		User userEntity = userRepository.mLogin(user.getUsername(), user.getPassword());
		if(userEntity == null) {
			return "loginfail";
		} else {
			return "login success";
		}
	}
	@DeleteMapping("/test/user/{id}")
	public String deleteById(@PathVariable int id) {
		userRepository.deleteById(id);
		return"delete ok";
	}
	// update 아이디로 데이터를 select로 가져온다.
	// 기존데이터를 날라가는것을 방지 할 수 있다.
	@PutMapping("/test/user/{id}")
	public String updateOne(@PathVariable int id, User user) { // user : password, email
		// 1.id로 select 하기
		User userEntity = userRepository.findById(id).get();
		
		// 2. 받은 body 데이터로 수정하기
		userEntity.setPassword(user.getPassword());
		userEntity.setEmail(user.getEmail());
		
		// 3. save하면 된다. (이때 꼭 id값이 db에 존재해야 update가 된다.)
		userRepository.save(userEntity); //id값이 들어오면 수정(update) id값이 안들어오면 저장.
		
		return "update ok";
	}
		
	
}









