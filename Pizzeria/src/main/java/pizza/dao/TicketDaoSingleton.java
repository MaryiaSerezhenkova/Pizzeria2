package pizza.dao;

import pizza.dao.api.DataSourceCreator;
import pizza.dao.api.ITicketDao;

public class TicketDaoSingleton {
	private TicketDao ticketDao;
    private volatile static TicketDaoSingleton instance;

    public TicketDaoSingleton() {
        try{
            this.ticketDao = new TicketDao(DataSourceCreator.getInstance());
        } catch (Exception e){
            throw new RuntimeException("Возникли проблемы с созданием слоя доступа к данным", e);
        }
    }

    public static ITicketDao getInstance() {
        if(instance == null){
            synchronized (TicketDaoSingleton.class){
                if(instance == null){
                    instance = new TicketDaoSingleton();
                }
            }
        }
        return instance.ticketDao;
    }
}


