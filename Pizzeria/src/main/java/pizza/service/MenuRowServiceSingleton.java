package pizza.service;

import pizza.dao.MenuRowDaoSingleton;
import pizza.service.api.IMenuRowService;

public class MenuRowServiceSingleton {

	private final IMenuRowService menuRowService;
	private volatile static MenuRowServiceSingleton firstInstance = null;

	public MenuRowServiceSingleton() {
		this.menuRowService = new MenuRowService(MenuRowDaoSingleton.getInstance());
	}

	public static IMenuRowService getInstance() {
		synchronized (MenuRowServiceSingleton.class) {
			if (firstInstance == null) {
				firstInstance = new MenuRowServiceSingleton();
			}
		}
		return firstInstance.menuRowService;
	}
}