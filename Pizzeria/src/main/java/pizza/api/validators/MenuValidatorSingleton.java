package pizza.api.validators;

import pizza.api.dto.MenuDTO;

public class MenuValidatorSingleton {
    private static volatile MenuValidatorSingleton instance;
    private final IValidator<MenuDTO> menuValidator;

    private MenuValidatorSingleton() {
        menuValidator = new MenuValidator();
    }

    public static IValidator<MenuDTO> getInstance() {
        if (instance == null) {
            synchronized (MenuValidatorSingleton.class) {
                if (instance == null) {
                    instance = new MenuValidatorSingleton();
                }
            }
        }

        return instance.menuValidator;
    }
}