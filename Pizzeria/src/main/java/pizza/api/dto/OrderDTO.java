package pizza.api.dto;

import java.util.List;

public class OrderDTO {
	
	private List<Items> selectedItems;

	public List<Items> getSelectedItems() {
		return selectedItems;
	}
	public void setSelectedItems(List<Items> selectedItems) {
		this.selectedItems = selectedItems;
	}
	public static class Items {
		
		private long menuRow;
		private int count;

		public long getMenuRow() {
			return menuRow;
		}

		public void setMenuRow(long menuRow) {
			this.menuRow = menuRow;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		
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
