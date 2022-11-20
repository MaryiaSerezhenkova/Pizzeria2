package pizza.api.validators;

import java.util.ArrayList;
import java.util.List;

import pizza.api.dto.OrderDTO;
import pizza.api.exceptions.ValidationException;

public class OrderValidator implements IValidator<OrderDTO> {
	@Override
	public void validate(OrderDTO orderDTO) {
		List<String> errors = new ArrayList<>();

		if (orderDTO == null) {
			errors.add("OrderDTO is null");
		} else {
			if (orderDTO.getSelectedItems().size() < 1) {
				errors.add("Order must have at least one item");
			}
		}

		if (errors.size() > 0) {
			throw new ValidationException(String.join(", ", errors));
		}
	}
}