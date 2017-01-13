package com.spring.ex1;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		String configLocation = "classpath:applicationCTX.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation); //xml파일 파싱해주는 부분.
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class); //bean을 꺼내옴.. (클래스이름, 참조된 타입)
		
		myCalculator.add();
		myCalculator.sub();
		myCalculator.mul();
		myCalculator.div();
		
		ctx.close();
	}
}
