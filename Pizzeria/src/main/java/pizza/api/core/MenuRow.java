package pizza.api.core;

import java.time.LocalDateTime;

import pizza.api.IMenuRow;
import pizza.api.IPizzaInfo;

public class MenuRow implements IMenuRow {
//	private long id;
//	private LocalDateTime dtCreate;
//	private LocalDateTime dtUpdate;
	private IPizzaInfo pizzaInfo;
	// private long pizzaInfoId;
	private double price;

//	private long menuId;
//	private IMenu menu;
//	
	public MenuRow() {
		super();
	}

	public MenuRow(IPizzaInfo pizzaInfo, double price) {
		super();
		this.pizzaInfo = pizzaInfo;
		this.price = price;
	}

	public MenuRow(long long1, LocalDateTime object, LocalDateTime object2, long long2, double double1, long long3) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public IPizzaInfo getInfo() {
		return pizzaInfo;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public void setPizzaInfo(IPizzaInfo pizzaInfo) {
		this.pizzaInfo = pizzaInfo;
	}

	@Override
	public void setPrice(double price) {
		this.price = price;

	}

}