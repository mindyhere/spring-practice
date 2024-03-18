package com.example.spring03.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if (session.getAttribute("admin_userid") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login.do?message=nologin");
			return false; // 요청을 더이상 실행하지 않음
		} else {
			return true; // 요청을 실행
		}
	}
}
