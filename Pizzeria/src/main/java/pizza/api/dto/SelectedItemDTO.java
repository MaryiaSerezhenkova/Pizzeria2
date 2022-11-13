package pizza.api.dto;

import pizza.api.IMenuRow;

public class SelectedItemDTO {

	private IMenuRow selectedItem;
	private int count;

	public SelectedItemDTO(IMenuRow selectedItem, int count) {
		super();
		this.selectedItem = selectedItem;
		this.count = count;
	}

	public IMenuRow getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(IMenuRow selectedItem) {
		this.selectedItem = selectedItem;
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
		builder.append("SelectedItemDTO [selectedItem=");
		builder.append(selectedItem);
		builder.append(", count=");
		builder.append(count);
		builder.append("]");
		return builder.toString();
	}

}