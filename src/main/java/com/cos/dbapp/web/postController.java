package com.cos.dbapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class postController {

	@GetMapping("/")
	public String home() {
		System.out.println("home 실행됨");
		return "index";
	}
}
