package com.example.spring03.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 세션객체 생성
		HttpSession session = request.getSession();
		if (session.getAttribute("userid") == null) {
			// 세션이 없으면 로그인페이지로 이동
			response.sendRedirect(request.getContextPath() + "/member/login.do?message=nologin");
			//  						  http://localhost/	
			return false; // 매인액션 실행 차단
		} else {
			return true; // 메인액션을 실행
		}
	}
}
