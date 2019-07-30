package com.shayne.test;

import org.springframework.beans.factory.InitializingBean;

public class InitializingBeanTest implements InitializingBean {

    private String name;

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBeanTest initializing...");
        this.name = "Lin";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /*init-method*/
	public void setOtherName(){
		System.out.println("InitializingBeanTest setOtherName...");
		this.name = "chuanye";
	}
}