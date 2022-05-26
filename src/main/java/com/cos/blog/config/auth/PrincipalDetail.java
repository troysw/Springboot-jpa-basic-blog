package com.cos.blog.config.auth;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.cos.blog.model.User;

import lombok.Getter;

// 스프릥 시큐리티가 로그인 요청을 가로채서 로그인을 진행하고 완료가 되면 UserDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션 저장소에 저장을 해준다.

@Getter
public class PrincipalDetail implements UserDetails{
	
	private User user; // 콤포지션 -> 객체를 품고 있는것을 콤포지션이라고 함

	public PrincipalDetail(User user) {
		this.user = user;
	}


	@Override
	public String getPassword() {
		
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getUsername();
	}

	
	// 계정이 만료 되지 않았는지 리턴한다. ( true 면 만료가 안됐다는뜻)
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	
	// 계정이 잠겨있지 않았는지 리턴한다. ( true 면 잠기지 않음)
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	

	// 비밀번호가 만료되지 않았는 지 리턴한다 ( true 면 만료가 아직 안됐다는 뜻)
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// 계정이 활성화(사용가능)인지 리턴한다 ( true면 활성화)
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	// 계정의 권한을 리턴함
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collectors = new ArrayList<>();
		collectors.add(()->{return "ROLE_" + user.getRole();});
		// 원래 권한이 많으면 for문을 돌려야함
		return collectors;
	}
	
//	collectors.add(new GrantedAuthority() { // GrantedAuthority 는 인터페이스라 익명 클래스 를 만듬
//	
//	@Override
//	public String getAuthority() { // GrantedAuthority 에 있는 추상메서드가 오버라이딩 되서 리턴값을 넣어주면 됨
//
//		return "ROLE_" +user.getRole(); // ROLE_USER, ROLE_ADMIN 식으로 리턴이 됨  앞에 ROLE 은 frefix처럼 꼭 붙여야되는 규칙 같은거임
//	}
//});
	
}
