package pizza.api.mapper;

import pizza.api.ITicket;
import pizza.api.core.Ticket;
import pizza.api.dto.TicketDTO;

public class TicketMapper {
	  public static ITicket ticketInputMapping(TicketDTO ticket) {
	        return new Ticket(ticket.getNumber(), ticket.getOrderId());
	    }

	    public static Ticket ticketOutputMapping(ITicket ticket) {
	        return new Ticket(ticket.getId(), ticket.getCreatAt(), ticket.getNumber(), ticket.getOrder());
	    }

}
