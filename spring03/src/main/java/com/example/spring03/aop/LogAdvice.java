package com.example.spring03.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // spring에서 관리하는 bean
@Aspect // aop를 지원하는 어드바이스 클래스
public class LogAdvice {

//	@Around("execution(* com.example.spring03.controller..*Controller.*(..))"
//			+ " || execution(* com.example.spring03.service..*Impl.*(..))"
//			+ " || execution(* com.example.spring03.model..*Impl.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = joinPoint.proceed();
		String type = joinPoint.getSignature().getDeclaringTypeName();
		String name = "";

		if (type.indexOf("Controller") != -1) {
			name = "Controller\t: ";
		} else if (type.indexOf("Service") != -1) {
			name = "Service\t: ";
		} else if (type.indexOf("DAO") != -1) {
			name = "DAO\t: ";
		}
		System.out.println(name + type + "." + joinPoint.getSignature().getName() + "()");
		System.out.println(Arrays.toString(joinPoint.getArgs()));

		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("실행시간: " + time);
		return result;
	}
}
