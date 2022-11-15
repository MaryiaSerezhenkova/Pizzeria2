package pizza.api.dto;

import java.util.List;

public class MenuDTO {

	private String name;
	private boolean enabled;
	private List<Row> items;

	public MenuDTO() {
		super();
	}

	public MenuDTO(String name, boolean enabled) {

		this.name = name;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public List<Row> getItems() {
		return items;
	}

	public void setItems(List<Row> items) {
		this.items = items;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public static class Row {
		private long pizzaInfo;
		private double price;

		public long getPizzaInfo() {
			return pizzaInfo;
		}

		public void setPizzaInfo(long pizzaInfo) {
			this.pizzaInfo = pizzaInfo;
		}

		public double getPrice() {
			return price;
		}

		public void setPrice(double price) {
			this.price = price;
		}
	}
}
