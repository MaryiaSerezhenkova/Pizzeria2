package pizza.dao.api;


import pizza.api.ITicket;

public interface ITicketDao extends IDao<ITicket> {
	void delete(long id);

}
