package pizza.api.core;

import java.time.LocalDateTime;
import java.util.List;

import pizza.api.IOrderStatus;
import pizza.api.IStage;
import pizza.api.ITicket;

public class OrderStatus implements IOrderStatus {

	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private ITicket ticket;
	private List<IStage> stages;
	private boolean isDone;

	public OrderStatus() {
		super();
	}

	public OrderStatus(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, ITicket ticket, List<IStage> stages,
			boolean isDone) {
		super();
		this.id = id;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.ticket = ticket;
		this.stages = stages;
		this.isDone = isDone;
	}

	@Override
	public ITicket getTicket() {
		return ticket;
	}

	@Override
	public List<IStage> getHistory() {
		return stages;
	}

	@Override
	public boolean isDone() {
		return isDone;
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

	public void setStages(List<IStage> stages) {
		this.stages = stages;
	}

	public void setTicket(ITicket ticket) {
		this.ticket = ticket;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

}
