package com.cos.blog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cos.blog.config.auth.PrincipalDetailService;


// 이 밑에 3개의 어노테이션은 시큐리티의 기본 3종세트라고 생각하면 됨
// bean 등록 -> 스프링 컨테이너에서 객체를 관리할 수 있게 해주는 거임
@Configuration //이건 BEAN 등록 ( IOC관리 )
@EnableWebSecurity // 이건  시큐리티 필터를 추가하는것 -> 스프링 시큐리티가 활성화 되있는데 어떤 설정을 해당 파일( 이 파일) 에서 하겠다는 뜻
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근하면 권한이랑 인증을 미리 체크하겠다는 뜻임
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private PrincipalDetailService principalDetailService;
	
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean // 리턴하는 암호화값을 IOC, 즉 스프링이 관리함
	public BCryptPasswordEncoder encodePWD() { // 이 객체를 통해서 비밀번호를 넣으면 암호화 가 가능함
		return new BCryptPasswordEncoder();
	}
	
	//시큐리티가 대신 로그인을 해주는데 password를 가로채기 하는데
	// 해당 password가 무엇으로 해쉬가 되어 회원가입이 되었는지 알아야
	// 같은 해쉬로 암호화해서 DB에 있는 해쉬랑 비교할 수 있음.
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(principalDetailService).passwordEncoder(encodePWD());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() // csrf 토큰 비활성화 (테스트시에는 disable 을 걸어두는게 좋음)
			.authorizeRequests() // 요청 받을때
				.antMatchers("/", "/auth/**", "/js/**", "/css/**", "/image/**") // /auth/ 를 거쳐서 들어오는거나, 여기있는 것들은 
				.permitAll() // 인증 없이도 그냥 ㅇㅋ 들어오셈 해주고
				.anyRequest() // 이 auth 를 거치는게 아닌 다른 모든 요청은
				.authenticated() // 인증이 돼야해 ㅇㅋ? 아님 못들어옴
			.and() //그리고 ?
				.formLogin() // 인증이 안됐는데 페이지 요청시 로그인 폼으로 끌고감
				.loginPage("/auth/loginForm") // 로그인 페이지
				.loginProcessingUrl("/auth/loginProc")// 로그인 프로세싱 감지, 시큐리티가 해당주소로 요청하는 로그인을 가로채서 대신 로그인을 해줌
				.defaultSuccessUrl("/") // 로그인 성공적으로 하면 -> 기본 패스로 감
				; 
				
			
		
		
	}
	
}
