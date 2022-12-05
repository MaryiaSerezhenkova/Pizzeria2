package main.service;

import pizza.service.api.IService;

public class ServiceA implements IServiceA {

	private IService B;

	public ServiceA(IService b) {
		super();
		B = b;
	} 
	
}
