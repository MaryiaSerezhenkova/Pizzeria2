package pizza.api.dto;

import java.util.List;

public class OrderDTO {
	private List<SelectedItemDTO> selectedItems;

	public OrderDTO(List<SelectedItemDTO> selectedItems) {
		super();
		this.selectedItems = selectedItems;
	}

	public List<SelectedItemDTO> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<SelectedItemDTO> selectedItems) {
		this.selectedItems = selectedItems;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("OrderDTO [selectedItems=");
		builder.append(selectedItems);
		builder.append("]");
		return builder.toString();
	}

}
