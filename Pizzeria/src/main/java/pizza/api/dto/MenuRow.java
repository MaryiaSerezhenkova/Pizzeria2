package pizza.api.dto;
import java.time.LocalDateTime;

import pizza.api.IMenuRow;
import pizza.api.IPizzaInfo;

public class MenuRow implements IMenuRow {
	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private PizzaInfo pizzaInfo;
	private double price;
	private Menu menu;

	public MenuRow(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, PizzaInfo pizzaInfo, double price,
			Menu menu) {
		super();
		this.id = id;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.pizzaInfo = pizzaInfo;
		this.price = price;
		this.menu = menu;
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

	public PizzaInfo getPizzaInfo() {
		return pizzaInfo;
	}

	public void setPizzaInfo(PizzaInfo pizzaInfo) {
		this.pizzaInfo = pizzaInfo;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public IPizzaInfo getInfo() {
		return this.pizzaInfo;
	}

	@Override
	public double getPrice() {
		return price;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MenuRow [id=");
		builder.append(id);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", dtUpdate=");
		builder.append(dtUpdate);
		builder.append(", pizzaInfo=");
		builder.append(pizzaInfo);
		builder.append(", price=");
		builder.append(price);
		builder.append(", menu=");
		builder.append(menu);
		builder.append("]");
		return builder.toString();
	}

}