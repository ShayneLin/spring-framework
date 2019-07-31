package com.shayne.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.convert.support.GenericConversionService;

public class CustomConverterTest {
	@Test
	/**
	 * @see GenericConversionService#convert(java.lang.Object, org.springframework.core.convert.TypeDescriptor, org.springframework.core.convert.TypeDescriptor)
	 */
	/*实现自定义的Converter调用*/
	public void placeHodlerTest(){
		ApplicationContext context = new ClassPathXmlApplicationContext("spring-customconverter.xml");
		StudentService studentService = (StudentService) context.getBean("student");
		System.out.println("student name:" + studentService.getStudentService().getName());
	}
}
