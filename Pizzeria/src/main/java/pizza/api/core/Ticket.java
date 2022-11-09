package pizza.api.core;

import java.time.LocalDateTime;

import pizza.api.IOrder;
import pizza.api.ITicket;

public class Ticket implements ITicket {

	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private IOrder order;



	public Ticket(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, IOrder order) {
		super();
		this.id = id;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.order = order;
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

	public LocalDateTime getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public void setOrder(IOrder order) {
		this.order = order;
	}

	public IOrder getOrder() {
		return order;
	}

	@Override
	public String getNumber() {
		return null;
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
		builder.append(", dtUpdate=");
		builder.append(dtUpdate);
		builder.append(", order=");
		builder.append(order);
		builder.append("]");
		return builder.toString();
	}

}