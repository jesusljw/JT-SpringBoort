package com.jt;

import java.util.Calendar;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestFactory {
	@Test
	public void testStaticFactory() {
		ApplicationContext context = new ClassPathXmlApplicationContext("/spring/factory.xml");
		Calendar calendar1 = (Calendar) context.getBean("calendar1");
		Calendar calendar2 = (Calendar) context.getBean("calendar2");
		Calendar calendar3 = (Calendar) context.getBean("calendar3");
		System.out.println("获取当前时间:"+calendar1.getTime());
		System.out.println("获取当前时间:"+calendar2.getTime());
		System.out.println("获取当前时间:"+calendar3.getTime());
	}
}
