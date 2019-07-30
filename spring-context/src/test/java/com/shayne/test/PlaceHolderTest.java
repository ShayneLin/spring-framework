package com.shayne.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PlaceHolderTest {

	@Test
	/*实现根据profile来加载指定的配置properties文件*/
	public void placeHodlerTest(){
		System.setProperty("spring.profiles.active", "prod");
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-placeholder.xml");

		StudentService studentService = (StudentService) context.getBean("studentService");
		System.out.println("student name:" + studentService.getName());
	}
}
