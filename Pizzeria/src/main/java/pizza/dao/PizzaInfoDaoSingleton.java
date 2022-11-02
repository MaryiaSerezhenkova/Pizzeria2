package pizza.dao;
import pizza.dao.api.DataSourceCreator;
import pizza.dao.api.IPizzaInfoDao;

public class PizzaInfoDaoSingleton {
	private PizzaInfoDao pizzaInfoDao;
	private volatile static PizzaInfoDaoSingleton instance;

	public PizzaInfoDaoSingleton() {
		try {
			this.pizzaInfoDao = (PizzaInfoDao) new PizzaInfoDao(DataSourceCreator.getInstance());
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
