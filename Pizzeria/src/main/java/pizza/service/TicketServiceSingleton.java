package pizza.service;

import pizza.dao.TicketDaoSingleton;
import pizza.service.api.ITicketService;

public class TicketServiceSingleton {
	 private final ITicketService ticketService;
    private volatile static TicketServiceSingleton firstInstance = null;

    public TicketServiceSingleton() {
        this.ticketService = new TicketService(TicketDaoSingleton.getInstance(), OrderServiceSingleton.getInstance());
    }

    public static ITicketService getInstance() {
        synchronized (TicketServiceSingleton.class) {
            if (firstInstance == null) {
                firstInstance = new TicketServiceSingleton();
            }
        }
        return firstInstance.ticketService;
    }
}