package pizza.api.dto;
import java.time.LocalDateTime;

import pizza.api.IPizzaInfo;

public class PizzaInfo implements IPizzaInfo {
	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private String name;
	private String description;
	private int size;

	public PizzaInfo(long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String name, String description,
			int size) {
		super();
		this.id = id;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.name = name;
		this.description = description;
		this.size = size;
	}

	public PizzaInfo() {
		// TODO Auto-generated constructor stub
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

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setSize(int size) {
		this.size = size;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PizzaInfo [id=");
		builder.append(id);
		builder.append(", dtCreate=");
		builder.append(dtCreate);
		builder.append(", dtUpdate=");
		builder.append(dtUpdate);
		builder.append(", name=");
		builder.append(name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", size=");
		builder.append(size);
		builder.append("]");
		return builder.toString();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getSize() {
		return size;
	}

}