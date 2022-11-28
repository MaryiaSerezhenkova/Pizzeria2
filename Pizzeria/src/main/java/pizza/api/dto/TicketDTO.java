package pizza.api.dto;

public class TicketDTO {

	private int number;
	private Long orderId;

	public TicketDTO() {
		super();
	}

	public TicketDTO(int number, Long orderId) {
		super();
		this.number = number;
		this.orderId = orderId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TicketDTO [number=");
		builder.append(number);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append("]");
		return builder.toString();
	}

}
