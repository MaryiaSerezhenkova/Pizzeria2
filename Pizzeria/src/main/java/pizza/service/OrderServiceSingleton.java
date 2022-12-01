package pizza.service;

import pizza.dao.OrderDaoSingleton;
import pizza.service.api.IOrderService;

public class OrderServiceSingleton {
	 private final IOrderService orderService;
	    private volatile static OrderServiceSingleton firstInstance = null;

	    public OrderServiceSingleton() {
	        this.orderService = new OrderService(OrderDaoSingleton.getInstance(), MenuServiceSingleton.getInstance());
	    }

	    public static IOrderService getInstance() {
	        synchronized (OrderServiceSingleton.class) {
	            if (firstInstance == null) {
	                firstInstance = new OrderServiceSingleton();
	            }
	        }
	        return firstInstance.orderService;
	    }
	}


