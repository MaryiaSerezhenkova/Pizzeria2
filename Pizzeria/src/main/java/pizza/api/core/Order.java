package pizza.api.core;

import java.time.LocalDateTime;
import java.util.List;

import pizza.api.IOrder;
import pizza.api.ISelectedItem;

public class Order implements IOrder {

	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private List<ISelectedItem> selectedItems;

	public Order(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, List<ISelectedItem> selectedItems) {
		super();
		this.id = id;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.selectedItems = selectedItems;
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

	public List<ISelectedItem> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<ISelectedItem> selectedItems) {
		this.selectedItems = selectedItems;
	}

	@Override
	public List<ISelectedItem> getSelected() {
		return selectedItems;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Order [id=");
		builder.append(id);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", dtUpdate=");
		builder.append(dtUpdate);
		builder.append(", selectedItems=");
		builder.append(selectedItems);
		builder.append("]");
		return builder.toString();
	}

}