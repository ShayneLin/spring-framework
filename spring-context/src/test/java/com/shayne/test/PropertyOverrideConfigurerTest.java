package com.shayne.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PropertyOverrideConfigurerTest {
	@Test
	/*实现PropertyOverrideConfigurer修改属性*/
	public void placeHodlerTest(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-propertyoverride.xml");
		StudentService studentService = (StudentService) context.getBean("student");
		System.out.println("student name:" + studentService.getName());
	}
}
