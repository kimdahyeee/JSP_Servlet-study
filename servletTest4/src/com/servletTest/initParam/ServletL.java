package com.servletTest.initParam;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletL implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("contextInitialized");
	}

}
