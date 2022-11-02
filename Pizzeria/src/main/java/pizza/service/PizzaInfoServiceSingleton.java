package pizza.service;

import pizza.dao.PizzaInfoDaoSingleton;
import pizza.service.api.IPizzaInfoService;

public class PizzaInfoServiceSingleton {
    private final IPizzaInfoService pizzaInfoService;
    private volatile static PizzaInfoServiceSingleton firstInstance = null;

    public PizzaInfoServiceSingleton() {
        this.pizzaInfoService = new PizzaInfoService(PizzaInfoDaoSingleton.getInstance());
    }

    public static IPizzaInfoService getInstance() {
        synchronized (PizzaInfoServiceSingleton.class) {
            if (firstInstance == null) {
                firstInstance = new PizzaInfoServiceSingleton();
            }
        }
        return firstInstance.pizzaInfoService;
    }
}