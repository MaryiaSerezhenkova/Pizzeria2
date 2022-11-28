package pizza.api.dto;

public class SelectedItemDTO {

	private long rowId;
	private int count;
	private long orderId;

	public SelectedItemDTO() {
		super();

	}

	public SelectedItemDTO(long rowId, int count, long orderId) {
		super();
		this.rowId = rowId;
		this.count = count;
		this.orderId = orderId;
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

	public long getRowId() {
		return rowId;
	}

	public void setRowId(long rowId) {
		this.rowId = rowId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SelectedItemDTO [rowId=");
		builder.append(rowId);
		builder.append(", count=");
		builder.append(count);
		builder.append(", orderId=");
		builder.append(orderId);
		builder.append("]");
		return builder.toString();
	}

}