package com.example.spring03.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component // spring에서 관리하는 bean
@Aspect // aop bean
public class MessageAdvice {
	@Before("execution(* com.example.spring03.service.message.MessageService*.*(..))")
	public void startLog(JoinPoint jp) {
		System.out.println("class: " + jp.getSignature());
		System.out.println("method: " + jp.getSignature().getName());
		System.out.println("args: " + Arrays.toString(jp.getArgs()));
	}

	@Around("execution(* com.example.spring03.service.message.MessageService*.*(..))")
	public Object timeLog(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		Object result = pjp.proceed();
		long end = System.currentTimeMillis();
		System.out.println(pjp.getSignature().getName() + " : " + (end - start));
		System.out.println("===============");
		return result;
	}
}
