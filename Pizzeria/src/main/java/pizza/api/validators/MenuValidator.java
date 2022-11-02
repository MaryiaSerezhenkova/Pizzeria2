package pizza.api.validators;

import java.util.ArrayList;
import java.util.List;

import pizza.api.dto.MenuDTO;

public class MenuValidator {
    public static void validate(MenuDTO menuDTO) {
        List<String> errors = new ArrayList<>();

        if (menuDTO.getName() == null || menuDTO.getName().isEmpty()) {
            errors.add("Menu name is required");
        }

        if (menuDTO.isEnabled() == false) {
            errors.add("Menu active is required");
        }

        if (errors.size() > 0) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }
}