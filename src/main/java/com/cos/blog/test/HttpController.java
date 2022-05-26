package com.cos.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청을 했는데 그 응답이 html이면?
// @controller 쓰면 됨

// 사용자가 요청을 하면 -> 응답(data)를 해주는 것
@RestController
public class HttpController {
	
	
	@GetMapping("/http/lombok")
	public String lombokTest() {
		Member m1 = Member.builder().username("ssar").password("1234").email("ssar@nate.com").build();
		System.out.println(TAG + "getter " + m1.getUsername());
		m1.setUsername("Cos");
		System.out.println(TAG + "Setter" + m1.getUsername());
		return "lombok test 완료";
	}
	
	private static final String TAG = "HttpController : ";

	// 인터넷 브라우저 요청은 무조건 get요청밖에 할 수 없다,
	// http://localhost:8080/http/get (Select)
	@GetMapping("/http/get")
	public String getTest(Member m) {
		return "get 요청 :" + m.getId() + ", " + m.getUsername() + " ," + m.getPassword() + ", " + m.getEmail();
	}

	// http://localhost:8080/http/post
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { // MessageConverter( 스프링부트 내부 시스템이 json을 오브젝트에 맞게 바꿔줌)
		return  "post 요청 :" + m.getId() + ", " + m.getUsername() + " ," + m.getPassword() + ", " + m.getEmail();
	}

	// http://localhost:8080/http/put
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put요청" + m.getId() + ", " + m.getUsername() + " ," + m.getPassword() + ", " + m.getEmail();
	}

	// http://localhost:8080/http/delete
	@DeleteMapping("/http/delete")
	public String deleteTest(@RequestBody Member m) {
		return "delete요청";
	}
	
}
