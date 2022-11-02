package pizza.service;

import pizza.dao.MenuDaoSingleton;
import pizza.service.api.IMenuService;

public class MenuServiceSingleton {
    private final IMenuService menuService;
    private volatile static MenuServiceSingleton firstInstance = null;

    public MenuServiceSingleton() {
        this.menuService = new MenuService(MenuDaoSingleton.getInstance());
    }

    public static IMenuService getInstance() {
        synchronized (MenuServiceSingleton.class) {
            if (firstInstance == null) {
                firstInstance = new MenuServiceSingleton();
            }
        }
        return firstInstance.menuService;
    }
}