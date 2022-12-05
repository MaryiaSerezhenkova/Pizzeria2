package main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pizza.api.IPizzaInfo;
import pizza.service.api.IPizzaInfoService;

public class main8 {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("pizza.xml");
		IPizzaInfoService service = context.getBean(IPizzaInfoService.class);
		List<IPizzaInfo> iPizzaInfos = service.get();
		
		for(IPizzaInfo iPizzaInfo:iPizzaInfos) {
		System.out.println(iPizzaInfo);
	}
}
}