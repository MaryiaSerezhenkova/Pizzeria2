package pizza.api.validators;

import java.util.ArrayList;
import java.util.List;

import pizza.api.dto.MenuDTO;
import pizza.api.exceptions.ValidationException;

public class MenuValidator implements IValidator<MenuDTO> {
	public void validate(MenuDTO menuDTO) {
		List<String> errors = new ArrayList<>();

		if (menuDTO.getName() == null || menuDTO.getName().isEmpty()) {
			errors.add("Menu name is required");
		}

		if (menuDTO.isEnabled() == false) {
			errors.add("Menu active is required");
		}

		if (errors.size() > 0) {
			throw new ValidationException(String.join(", ", errors));
		}
	}
}