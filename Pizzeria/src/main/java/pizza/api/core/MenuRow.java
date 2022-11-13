package pizza.api.core;

import java.time.LocalDateTime;

import pizza.api.IMenu;
import pizza.api.IMenuRow;
import pizza.api.IPizzaInfo;

public class MenuRow implements IMenuRow {
	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private IPizzaInfo pizzaInfo;
	private long pizzaInfoId;
	private double price;
	private long menuId;
	private IMenu menu;
	
	public MenuRow() {
		super();
	}


	public MenuRow(long pizzaInfoId, double price, long menuId) {
		super();
		this.pizzaInfoId = pizzaInfoId;
		this.price = price;
		this.menuId = menuId;
	}

	public MenuRow(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, long pizzaInfoId, double price,
			long menuId) {
		super();
		this.id = id;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.pizzaInfoId = pizzaInfoId;
		this.price = price;
		this.menuId = menuId;
	}

	public MenuRow(long id, IPizzaInfo pizzaInfo, LocalDateTime dtCreate, LocalDateTime dtUpdate, long pizzaInfoId, double price,
			long menuId) {
		super();
		this.id = id;
		this.pizzaInfo=pizzaInfo;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.pizzaInfoId = pizzaInfoId;
		this.price = price;
		this.menuId = menuId;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPizzaInfoId() {
		return pizzaInfoId;
	}

	public void setPizzaInfoId(long pizzaInfoId) {
		this.pizzaInfoId = pizzaInfoId;
	}

	public long getMenuId() {
		return menuId;
	}

	public void setMenuId(long menuId) {
		this.menuId = menuId;
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
	public void setMenu(IMenu menu) {
		this.menu = menu;

	}

	@Override
	public void setPrice(double price) {
		this.price = price;

	}

	@Override
	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	@Override
	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;

	}

	@Override
	public LocalDateTime getDtUpdate() {
		return dtUpdate;
	}

	@Override
	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;

	}

	@Override
	public IMenu getMenu() {
		return menu;
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
		builder.append(", pizzaInfoId=");
		builder.append(pizzaInfoId);
		builder.append(", price=");
		builder.append(price);
		builder.append(", menuId=");
		builder.append(menuId);
		builder.append("]");
		return builder.toString();
	}

}