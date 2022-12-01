package pizza.api.core;

import java.time.LocalDateTime;

import pizza.api.IOrder;
import pizza.api.ITicket;

public class Ticket implements ITicket {

	private long id;
	private LocalDateTime dtCreate;
	private int number;
	private IOrder order;

	public Ticket() {
		super();
	}

	public Ticket(long id, LocalDateTime dtCreate, int number, IOrder order) {
		super();
		this.id = id;
		this.dtCreate = dtCreate;
		this.number = number;
		this.order = order;
	}

	public Ticket(int number, Long orderId) {
		super();
		this.number = number;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setOrder(IOrder order) {
		this.order = order;
	}

	public IOrder getOrder() {
		return order;
	}

	@Override
	public LocalDateTime getCreatAt() {
		return dtCreate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Ticket [id=");
		builder.append(id);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", number=");
		builder.append(number);
		builder.append(", order=");
		builder.append(order);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int getNumber() {
		return number;
	}

	@Override
	public void setOrder(long id) {
		// TODO Auto-generated method stub
		
	}

}