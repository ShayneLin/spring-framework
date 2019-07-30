package com.shayne.test;

import org.junit.Test;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class TestDemo {


	/**
	 * @see AbstractAutowireCapableBeanFactory#initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)
	 */
	/*测试后置处理器*/
	@Test
	public void beanPostProcessorTest(){
		/*DefaultListableBeanFactory的方式需要手动注册*/
		ClassPathResource resource = new ClassPathResource("spring.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
		factory.addBeanPostProcessor(new BeanPostProcessorTest());//将指定 BeanPostProcessor 注册到该 BeanFactory 创建的 bean 中
		BeanPostProcessorTest test = (BeanPostProcessorTest) factory.getBean("beanPostProcessorTest");
		test.display();

		/*ApplicationContext实现了自动注册，测试发现注册了后面好像又没有了*/
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
		BeanPostProcessorTest testBean = (BeanPostProcessorTest) applicationContext.getBean("beanPostProcessorTest");
		testBean.display();


	}


	/**
	 * @see AbstractAutowireCapableBeanFactory#initializeBean(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)
	 * @see AbstractAutowireCapableBeanFactory#invokeInitMethods(java.lang.String, java.lang.Object, org.springframework.beans.factory.support.RootBeanDefinition)
	 */
	/*测试InitializingBean和init-method，init-method在InitializingBean后面执行*/
	@Test
	public void initializingBeanTest(){
		ClassPathResource resource = new ClassPathResource("spring.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
		InitializingBeanTest test = (InitializingBeanTest) factory.getBean("initializingBeanTest");
		System.out.println("name:" + test.getName());
	}


	@Test
	/*测试生命周期*/
	public void lifeCycleBeanTest(){
		ClassPathResource resource = new ClassPathResource("spring.xml");
		DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
		XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
		reader.loadBeanDefinitions(resource);
		factory.addBeanPostProcessor(new LifeCycleBean()); // <1>
		LifeCycleBean lifeCycleBean = (LifeCycleBean) factory.getBean("lifeCycle");
		lifeCycleBean.display();

		System.out.println("方法调用完成，容器开始关闭....");
		// 关闭容器
		factory.destroySingletons();
	}

	@Test
	/*测试BeanFactoryPostProcessor修改BeanDefinition*/
	public void beanFactoryPostProcessorTest(){
		System.out.println("--------------测试BeanFactoryPostProcessor修改BeanDefinition------------------------");
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		StudentService studentService = (StudentService) context.getBean("studentService");
		System.out.println("student name:" + studentService.getName() + "-- age:" + studentService.getAge());
		/*一般情况下我们是不会主动去自定义 BeanFactoryPostProcessor ，其实 Spring 为我们提供了几个常用的
		 BeanFactoryPostProcessor，他们是PropertyPlaceholderConfigurer 和 PropertyOverrideConfigurer ，
		 其中 PropertyPlaceholderConfigurer 允许我们在 XML 配置文件中使用占位符并将这些占位符所代表的资源
		 单独配置到简单的 properties 文件中来加载，PropertyOverrideConfigurer 则允许我们使用占位符来明确表明bean
		 定义中的 property 与 properties 文件中的各配置项之间的对应关系，这两个类在我们大型项目中有非常重要的作用
		 */
	}
}
