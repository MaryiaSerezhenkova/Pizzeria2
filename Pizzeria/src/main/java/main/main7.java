package main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import main.service.IServiceA;

public class main7 {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
		IServiceA bean = context.getBean(IServiceA.class);
		System.out.println(bean);
	}

}
