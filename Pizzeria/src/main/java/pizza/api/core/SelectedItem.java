package pizza.api.core;

import pizza.api.IMenuRow;
import pizza.api.IOrder;
import pizza.api.ISelectedItem;

public class SelectedItem implements ISelectedItem {

	private IMenuRow row;
	private int count;
	private IOrder order;

	public SelectedItem() {
		super();
	}

	public SelectedItem(IMenuRow row, int count) {
		super();
		this.row = row;
		this.count = count;
	}

	public void setSelectedItem(IMenuRow row) {
		this.row = row;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public IMenuRow getRow() {
		return row;
	}

	public int getCount() {
		return count;
	}

	public IOrder getOrder() {
		return order;
	}

	public void setOrder(IOrder order) {
		this.order = order;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SelectedItem [row=");
		builder.append(row);
		builder.append(", count=");
		builder.append(count);
		builder.append(", order=");
		builder.append(order);
		builder.append("]");
		return builder.toString();
	}

}