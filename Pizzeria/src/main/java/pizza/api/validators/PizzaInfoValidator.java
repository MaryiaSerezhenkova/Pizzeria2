package pizza.api.validators;

import java.util.ArrayList;
import java.util.List;

import pizza.api.dto.PizzaInfoDto;

public class PizzaInfoValidator {
    public static void validate(PizzaInfoDto pizzaInfoDTO) throws Exception {
        List<String> errors = new ArrayList<>();

        if (pizzaInfoDTO == null) {
            errors.add("PizzaInfoDTO is null");
        } 

            if (pizzaInfoDTO.getSize() < 1E-6) {
                errors.add("Size is too small");
            }

        if (errors.size() > 0) {
            throw new Exception(String.join(", ", errors));
        }
    }
}