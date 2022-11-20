package pizza.api.dto;


public class SelectedItemDTO {

	private long orderId;
	private int count;

	public SelectedItemDTO() {
		super();

	}

	public SelectedItemDTO(long orderId, int count) {
		super();
		this.orderId = orderId;
		this.count = count;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SelectedItemDTO [orderId=");
		builder.append(orderId);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}

}