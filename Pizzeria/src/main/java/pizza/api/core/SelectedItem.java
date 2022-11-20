package pizza.api.core;

import pizza.api.IMenuRow;
import pizza.api.ISelectedItem;

public class SelectedItem implements ISelectedItem {

	private IMenuRow menuRow;
	private int count;

	public SelectedItem() {
		super();
	}

	public SelectedItem(IMenuRow menuRow, int count) {
		super();
		this.menuRow = menuRow;
		this.count = count;
	}

	public void setSelectedItem(IMenuRow menuRow) {
		this.menuRow = menuRow;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public IMenuRow getRow() {
		return menuRow;
	}

	public int getCount() {
		return count;
	}

}