package com.example.spring03.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // 환경설정 bean
public class WebMvcConfig implements WebMvcConfigurer {
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 인터셉터 클래스
		InterceptorRegistration reg1 = registry.addInterceptor(new SampleInterceptor());

		// 적용 패턴 추가
		reg1.addPathPatterns("/shop/**"); // ** → 모든 subdirectory를 포함

		InterceptorRegistration reg2 = registry.addInterceptor(new LoginInterceptor());
		reg2.addPathPatterns("/shop/cart/insert.do");
		reg2.addPathPatterns("/shop/cart/list.do");

		InterceptorRegistration reg3 = registry.addInterceptor(new AdminInterceptor());
		reg3.addPathPatterns("/shop/product/write.do");
		reg3.addPathPatterns("/shop/product/insert.do");
	}
}
