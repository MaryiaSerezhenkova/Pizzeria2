package pizza.api.core;

import java.time.LocalDateTime;
import java.util.List;

import pizza.api.IMenu;
import pizza.api.IMenuRow;

public class Menu implements IMenu {
	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private String name;
	private boolean enabled;

	private List<IMenuRow> items;

	public Menu(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, boolean enabled) {
		this.id = id;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.name = name;
		this.enabled = enabled;
	}

	public void setItems(List<IMenuRow> items) {
		this.items = items;
	}

	public Menu() {
	}

	public Menu(List<IMenuRow> items) {
		this.items = items;
	}

	public Menu(String name, Boolean enable) {
		this.name = name;
		this.enabled = enable;
	}

	public Menu(Long id, String name, Boolean enable) {
		this.id = id;
		this.name = name;
		this.enabled = enable;
	}

	public long getId() {
		return id;
	}

	public LocalDateTime getDtCreate() {
		return dtCreate;
	}

	public LocalDateTime getDtUpdate() {
		return dtUpdate;
	}

	public String getName() {
		return name;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public List<IMenuRow> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Menu{" + "id=" + id + ", dtCreate=" + dtCreate + ", dtUpdate=" + dtUpdate + ", name='" + name + '\''
				+ ", enabled=" + enabled + '}';
	}

	public void setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
	}

	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
