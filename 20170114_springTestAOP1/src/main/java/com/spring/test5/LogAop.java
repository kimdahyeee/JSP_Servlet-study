package com.spring.test5;
import org.aspectj.lang.ProceedingJoinPoint;

public class LogAop {

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
}
