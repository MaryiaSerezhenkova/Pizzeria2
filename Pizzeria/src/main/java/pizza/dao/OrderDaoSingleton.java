package pizza.dao;

import pizza.dao.api.DataSourceCreator;
import pizza.dao.api.IOrderDao;

public class OrderDaoSingleton {
	private OrderDao orderDao;
    private volatile static OrderDaoSingleton instance;

    public OrderDaoSingleton() {
        try{
            this.orderDao = (OrderDao) new OrderDao(DataSourceCreator.getInstance());
        } catch (Exception e){
            throw new RuntimeException("Возникли проблемы с созданием слоя доступа к данным", e);
        }
    }

    public static IOrderDao getInstance() {
        if(instance == null){
            synchronized (OrderDaoSingleton.class){
                if(instance == null){
                    instance = new OrderDaoSingleton();
                }
            }
        }
        return instance.orderDao;
    }
}



