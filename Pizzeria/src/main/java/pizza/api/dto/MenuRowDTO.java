package pizza.api.dto;

import pizza.api.core.Menu;
import pizza.api.core.PizzaInfo;

public class MenuRowDTO {
	
	private PizzaInfo pizzaInfo;
	private double price;
	private Menu menu;
	public MenuRowDTO(PizzaInfo pizzaInfo, double price, Menu menu) {
		super();
		this.pizzaInfo = pizzaInfo;
		this.price = price;
		this.menu = menu;
	}
	public PizzaInfo getPizzaInfo() {
		return pizzaInfo;
	}
	public void setPizzaInfo(PizzaInfo pizzaInfo) {
		this.pizzaInfo = pizzaInfo;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
}
