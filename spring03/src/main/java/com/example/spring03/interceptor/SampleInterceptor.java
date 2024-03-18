package com.example.spring03.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SampleInterceptor implements HandlerInterceptor {

	// 액션 수행 전에 경유
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("pre handle...");
		return true;
		// 메인액션 진행여부
		// true:메인액션으로 계속 진행 ↔ false:메인액션을 실행하지 않고 멈춤
	}

	// 액션 수행 후에 경유
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("post handle...");
	}
}
