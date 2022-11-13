package pizza.api.validators;

import pizza.api.dto.PizzaInfoDto;

public class PizzaInfoValidatorSingleton {
	private static volatile PizzaInfoValidatorSingleton instance;

	private final IValidator<PizzaInfoDto> pizzaInfoValidator;

	private PizzaInfoValidatorSingleton() {
		pizzaInfoValidator = new PizzaInfoValidator();
	}

	public static IValidator<PizzaInfoDto> getInstance() {
		if (instance == null) {
			synchronized (PizzaInfoValidatorSingleton.class) {
				if (instance == null) {
					instance = new PizzaInfoValidatorSingleton();
				}
			}
		}

		return instance.pizzaInfoValidator;
	}
}