package pizza.api.validators;

import pizza.api.dto.OrderDTO;

public class OrderValidatorSingleton {
	 private static volatile OrderValidatorSingleton instance;
	    private final IValidator<OrderDTO> orderValidator;

	    private OrderValidatorSingleton() {
	        orderValidator = new OrderValidator();
	    }

	    public static IValidator<OrderDTO> getInstance() {
	        if (instance == null) {
	            synchronized (OrderValidatorSingleton.class) {
	                if (instance == null) {
	                    instance = new OrderValidatorSingleton();
	                }
	            }
	        }

	        return instance.orderValidator;
	    }
	}


