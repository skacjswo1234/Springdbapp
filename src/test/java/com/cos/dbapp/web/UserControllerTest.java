package com.cos.dbapp.web;

import org.junit.jupiter.api.Test;

public class UserControllerTest {
	
	@Test	
	public void 주소파싱() {
		String addr = " http://localhost:8000/user/2";
		String host = "http://localhost:8000";

		// 스프링프레임워크 틀로 주소자르기 방식
		// replace부분은 host까지 자르지않겠다는 방식
		String r1 = addr.replace(host, "");
		// split 부분은 /부터 찾겠다는 의미 startIndex로하겠다는의미
		String[] r2 = r1.split("/");
		// r2로 만든부분을 출력
		System.out.println(r2[2]);
		System.out.println("주소파싱 테스트");
	}
	
	// 기존 자바로 서브스트링을 이용해서 문자열 자르기 찾는 방식
	public void 서브스트링() {
		String addr = "{2}";
		int startIndex = addr.indexOf("{");
		int endlndex = addr.indexOf("}");
		System.out.println(startIndex);
		System.out.println(endlndex);
		String result = addr.substring(startIndex+1,endlndex);
		System.out.println(result);
	}
	
}
