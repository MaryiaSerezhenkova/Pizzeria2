package pizza.api.dto;

import java.util.List;

import pizza.api.IStage;
import pizza.api.ITicket;

public class OrderStatusDTO {
	
	private ITicket ticket;
	private List<IStage> stages;
	private boolean isDone;
	
	public OrderStatusDTO() {
		super();
	}

	public OrderStatusDTO(ITicket ticket, List<IStage> stages, boolean isDone) {
		super();
		this.ticket = ticket;
		this.stages = stages;
		this.isDone = isDone;
	}

	public ITicket getTicket() {
		return ticket;
	}

	public void setTicket(ITicket ticket) {
		this.ticket = ticket;
	}

	public List<IStage> getStages() {
		return stages;
	}

	public void setStages(List<IStage> stages) {
		this.stages = stages;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}


}
