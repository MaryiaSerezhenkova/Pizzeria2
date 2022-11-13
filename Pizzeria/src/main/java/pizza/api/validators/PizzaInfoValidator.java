package pizza.api.validators;

import java.util.ArrayList;
import java.util.List;

import pizza.api.dto.PizzaInfoDto;
import pizza.api.exceptions.ValidationException;

public class PizzaInfoValidator  implements IValidator<PizzaInfoDto> {
	public PizzaInfoValidator() {
		super();
	}

	@Override
	public void validate(PizzaInfoDto pizzaInfoDTO) {

		List<String> errors = new ArrayList<>();

		if (pizzaInfoDTO == null) {
			errors.add("PizzaInfoDTO is null");
		}
		if (pizzaInfoDTO.getName() == null || pizzaInfoDTO.getName().isBlank()) {
			errors.add("Name is not valid");
		}
		if (pizzaInfoDTO.getDescription() == null || pizzaInfoDTO.getDescription().isBlank()) {
			errors.add("Description is not valid");
		}
		if (pizzaInfoDTO.getSize() <= 0) {
			errors.add("Size is  not valid");
		}

		if (errors.size() > 0) {
			throw new ValidationException(String.join(", ", errors));
		}
	}
}