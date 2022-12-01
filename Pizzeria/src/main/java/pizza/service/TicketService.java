package pizza.service;

import java.time.LocalDateTime;
import java.util.List;
import pizza.api.ITicket;
import pizza.api.core.Ticket;
import pizza.api.dto.TicketDTO;
import pizza.api.mapper.TicketMapper;
import pizza.api.validators.TicketValidator;
import pizza.dao.api.ITicketDao;
import pizza.service.api.IOrderService;
import pizza.service.api.ITicketService;

public class TicketService implements ITicketService {
	
	private final ITicketDao ticketDao ;

    private final IOrderService orderService;

    private static TicketValidator validator;
    

	public TicketService(ITicketDao ticketDao, IOrderService orderService) {
		super();
		this.ticketDao = ticketDao;
		this.orderService = orderService;
	}

	@Override
	public ITicket create(TicketDTO dto) {
		ITicket ticket = new Ticket();
		ticket.setDtCreate(LocalDateTime.now());
		ticket.setNumber(dto.getNumber());
		ticket.setOrder(dto.getOrderId());
		return this.ticketDao.create(ticket);
	}
	

	@Override
	public ITicket read(long id) {
		return TicketMapper.ticketOutputMapping(ticketDao.read(id));
	}

	@Override
	public List<ITicket> get() {
		return ticketDao.get();
	}

	@Override
	public ITicket update(long id, LocalDateTime dtUpdate, TicketDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
	}

}
