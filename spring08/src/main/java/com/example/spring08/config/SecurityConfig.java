package com.example.spring08.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.spring08.service.FailureHandler;
import com.example.spring08.service.SuccessHandler;

@Configuration  // 스프링설정
@EnableWebSecurity  // 보안설정 활성화
public class SecurityConfig {

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http
			.csrf(AbstractHttpConfigurer::disable)  // csrf 비활성화
			.authorizeHttpRequests(
				request -> request  // 람다식 표현법
					.requestMatchers("/admin/**").hasAnyRole("ADMIN")
					//		/admin 의 모든 하위 디렉토리	     역할 부여 관리자
					.requestMatchers("/**").permitAll()
					// 			http://localhost/... 모두 허용
			)
			.formLogin(
				form -> form
					.loginPage("/user/login.do")  // login url
					.loginProcessingUrl("/user/login_check.do")  // 로그인 처리 url
					.usernameParameter("userid")  // 아이디 변수명
					.passwordParameter("passwd")  // 비번 변수명 
					.successHandler(new SuccessHandler())  // 로그인 성공
					.failureHandler(new FailureHandler())  // 로그인 실패
			);
		return http.build();
	}
	
//	cf. 람다식
//	a(입력값) {			▶	 a(입력값 -> 리턴값);
//		return 리턴값
//	};

	@Bean
	PasswordEncoder pwdEncoder() {
		return new BCryptPasswordEncoder();  // 암호화 객체
	}
}
