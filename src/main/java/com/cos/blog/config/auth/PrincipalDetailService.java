package com.cos.blog.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;

@Service
public class PrincipalDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	// 스프링이 로그인 요청을 가로챌 때, username, password 변수 2개를 가로채는데
	// password 부분 처리는 알아서 함
	// username이 DB에 있는지만 확인해서 return 해주면 됨
	// 그 확인을 이 메서드가 하는거임
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User principal = userRepository.findByUsername(username) // user가 있는 사람이면 principal을 리턴
				.orElseThrow(()->{
					return new UsernameNotFoundException(username + "(은)는 찾을 수 없는 사용자 입니다."); // 없으면 이거 리턴
				});
		return new PrincipalDetail(principal); // 리턴 할 때 시큐리티의 세션에 유저 정보가 저장이 됨 (( 타입은 userDetail 타입이어야 한다))
	}
}
