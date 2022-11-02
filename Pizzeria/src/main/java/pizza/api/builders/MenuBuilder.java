package pizza.api.builders;

import java.time.LocalDateTime;
import java.util.List;
import pizza.api.IMenuRow;
import pizza.api.core.Menu;

public class MenuBuilder {
	private Long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private String name;
	private boolean enabled;

	private List<IMenuRow> items;

	private MenuBuilder() {
	}

	public static MenuBuilder create() {
		return new MenuBuilder();
	}

	public MenuBuilder setId(Long id) {
		this.id = id;
		return this;
	}

	public MenuBuilder setName(String name) {
		this.name = name;
		return this;
	}

	public MenuBuilder setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
		return this;
	}

	public MenuBuilder setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
		return this;
	}

	public MenuBuilder setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public MenuBuilder setItems(List<IMenuRow> items) {
		this.items = items;
		return this;
	}

	public Menu build() {
		Menu menu = new Menu(id, dtCreate, dtUpdate, name, enabled);
		menu.setItems(items);
		return menu;
	}
}