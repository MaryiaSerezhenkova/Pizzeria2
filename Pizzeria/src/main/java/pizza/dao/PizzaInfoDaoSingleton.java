package pizza.dao;
import pizza.dao.api.DataSourceCreator;
import pizza.dao.api.IPizzaInfoDao;

public class PizzaInfoDaoSingleton {
	private final IPizzaInfoDao pizzaInfoDao;
	private volatile static PizzaInfoDaoSingleton instance=null;

	public PizzaInfoDaoSingleton() {
		try {
			this.pizzaInfoDao = new PizzaInfoDao(DataSourceCreator.getInstance());
		} catch (Exception e) {
			throw new RuntimeException("Возникли проблемы с созданием слоя доступа к данным", e);
		}
	}

	public static IPizzaInfoDao getInstance() {
		if (instance == null) {
			synchronized (PizzaInfoDaoSingleton.class) {
				if (instance == null) {
					instance = new PizzaInfoDaoSingleton();
				}
			}
		}
		return instance.pizzaInfoDao;
	}
}
