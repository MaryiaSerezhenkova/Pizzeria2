package pizza.api.core;

import java.time.LocalDateTime;

import pizza.api.IMenuRow;
import pizza.api.ISelectedItem;

public class SelectedItem implements ISelectedItem {

	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private IMenuRow selectedItem;
	private int count;

	public SelectedItem(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, IMenuRow selectedItem, int count) {
		super();
		this.id = id;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.selectedItem = selectedItem;
		this.count = count;
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

	public IMenuRow getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(IMenuRow selectedItem) {
		this.selectedItem = selectedItem;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public IMenuRow getRow() {
		return selectedItem;
	}

	public int getCount() {
		return count;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SelectedItem [id=");
		builder.append(id);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", dtUpdate=");
		builder.append(dtUpdate);
		builder.append(", selectedItem=");
		builder.append(selectedItem);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}

}