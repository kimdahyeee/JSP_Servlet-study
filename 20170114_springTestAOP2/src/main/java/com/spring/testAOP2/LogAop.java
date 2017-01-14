package com.spring.testAOP2;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LogAop {

	@Pointcut("within(com.spring.testAOP2.*)")
	private void pointCutMethod(){
		
	}
	
	@Around("pointCutMethod()")
	public Object loggerAop(ProceedingJoinPoint jointPoint) throws Throwable{
		String signatureStr = jointPoint.getSignature().toShortString();
		System.out.println(signatureStr + "is start");
		long st = System.currentTimeMillis();
		
		try {
			Object obj = jointPoint.proceed(); //실행하는 부분.
			return obj;
		} finally {
			long et = System.currentTimeMillis();
			System.out.println(signatureStr + "is finished");
			System.out.println(signatureStr + "경과시간: "+(et-st));
		}
	}
	
	@Before("within(com.spring.testAOP2.*)")
	public void beforeAdvice(){
		System.out.println("beforeAdvice()");
	}
}
