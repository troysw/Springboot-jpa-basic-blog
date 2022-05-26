package com.cos.blog.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;


@Service // IOC
public class UserService {
	
	@Autowired // DI
	private UserRepository userRepository;
	

	@Autowired // DI 가 되어 주입됨
	private BCryptPasswordEncoder encoder;
	
	
	@Transactional
	public void memberJoin(User user) { // 회원 가입
		String rawPassword = user.getPassword(); // 쌩 날것의 비밀번호
		String encPassword = encoder.encode(rawPassword); // 해쉬 비밀번호
		user.setPassword(encPassword); // 그 해쉬 비밀번호를 세팅
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
	
	@Transactional
	public void userUpdate(User user) {
		//수정할때는 영속성 컨텍스트 에 USER 오브젝트를 영속화시키고, 영속화된 user 오브젝트를 수정하는게 베스트
		//select 를 해서 user오브젝트를 db로 부터 가져오는 이유는 영속화를 하기 위해서;
		// 영속화된 오브젝트를 변경하면 자동으로 db에 update문을 날려주거든요
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원 찾기 실패");
		});
		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);
		persistance.setPassword(encPassword);
		persistance.setEmail(user.getEmail());
		

		// 회원 수정 함수 종료시 => 서비스 가 종료 => 트랜잭션 종료 => 커밋 자동 완료
		// 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되서 변화된 정보를 자동으로 update 문을 날려 db에 저장시키게 함
	}
	
}
