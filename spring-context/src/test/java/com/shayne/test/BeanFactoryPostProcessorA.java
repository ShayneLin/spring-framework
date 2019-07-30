package com.shayne.test;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;

/*BeanFactoryPostProcessor主要是修改BeanDefinition与BeanPostProcessor 不一样，BeanPostProcessor 是修改Bean*/
public class BeanFactoryPostProcessorA implements BeanFactoryPostProcessor, Ordered {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("调用 BeanFactoryPostProcessorA ...");

        System.out.println("容器中有 BeanDefinition 的个数：" + beanFactory.getBeanDefinitionCount());

        // 获取指定的 BeanDefinition
        BeanDefinition bd = beanFactory.getBeanDefinition("studentService");

        MutablePropertyValues pvs = bd.getPropertyValues();

        pvs.addPropertyValue("name","shunzi");
        pvs.addPropertyValue("age",15);
    }

    @Override
    public int getOrder() {
        return 1;
    }
}