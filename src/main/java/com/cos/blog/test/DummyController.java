package com.cos.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


// restController 여서 html파일이 아니라 data 타입을 리턴해주는 controller = restcontroller
@RestController
public class DummyController {
	
	@Autowired // 의존성 주입 (di)
	private UserRepository userRepository;
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id) {
		
		try {
			userRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			return "삭제에 실패했습니다 ㅋ 해당 아이디는 DB 에 없는 거라고 ㅋㅋ";
		}
		return "삭제 되었습니다 . ID = " + id;
	}
	
	// email, password 를 수정 할 것
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User requestUser) { //html에서 json 데이터를 요청 -> 스프링 메세지 컨버터의 잭슨 라이브러리가 java 오브젝트로 변환 해서 받아줌
		
		System.out.println("id = " + id);
		System.out.println("password = " + requestUser.getPassword());
		System.out.println("email = " + requestUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정에 대실패 하셨습니다 ㅋㅋ ");
		});
		user.setPassword(requestUser.getPassword());
		user.setEmail(requestUser.getEmail());
		
//		userRepository.save(requestUser);
		return user;
	}
	
	//유저 전체를 받는법
	@GetMapping("/dummy/users")
	public List<User> list(){ //리스트로 객체타입을 유저로 설정
		
		return userRepository.findAll(); //유저 레포지토리에서 findAll로 db 저장소에 있는 유저 모두를 불러와서 응답함
		
	}
	
	
	//한 페이지당 2건의 데이터를 리턴 받아 볼 예정
	@GetMapping("/dummy/user")
	public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
		
		Page<User> pagingUser = userRepository.findAll(pageable);
		
		List<User> users = pagingUser.getContent();
		return users;
		
	}
	
	//{id} -> 주소로 파라미터를 전달 받을 수 있음
	// localhost:8000/blog/dummy/user/3 하면 {id} 에 3이들어감
	@GetMapping("/dummy/user/{id}")
	public User detail(@PathVariable int id) {
		
		//만약 user/4 를 찾으면 내가 데이터베이스에서 못찾아오게 되고 user가 null이 될것 아님?
		//그럼 return null 이 리턴되고 그러면 프로그램에 문제가 생기니까
		//Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해
		
		
		//람다식은 이프로젝트에서 안쓸것
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
		public IllegalArgumentException get() {
				
			return new IllegalArgumentException("해당 사용자는 없습니다.  ");
		}
		});
		
		//user 객체 = 자바 오브젝트 
		//요청은 웹브라우저에서 함
		// 웹브라우저에서 요청했기때문에 자바 오브젝트를 그대로 return user하면 말을 못알아듣기 때문에
		// 변환을 해줘야한다. (웹 브라우저가 이해할 수 있는 데이터) -> Json
		// 스프링부트는 메세지 컨버터라는 애가 응답시에 자동으로 작동 하기 때문에 
		// 자동으로 user 오브젝트를 json 으로 변환 해서 브라우저에게 던져줌!
		return user;
	}
	@PostMapping("/dummy/join")
	public String join(User user) {
		System.out.println("username = " + user.getUsername());
		System.out.println("password = " + user.getPassword());
		System.out.println("email = " + user.getEmail());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입이 완료 되었습니다";
	}
	
}
