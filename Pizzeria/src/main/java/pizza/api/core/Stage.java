package pizza.api.core;

import java.time.LocalDateTime;
import pizza.api.IStage;

public class Stage implements IStage {

	private Long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private String description;
	private int size;

	public Stage() {
		super();
	}

	public Stage(Long id, LocalDateTime dtCreate, LocalDateTime dtUpdate, String description, int size) {
		super();
		this.id = id;
		this.dtCreate = dtCreate;
		this.dtUpdate = dtUpdate;
		this.description = description;
		this.size = size;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;

	}

	@Override
	public LocalDateTime getTime() {
		return dtCreate;
	}

	@Override
	public void setTime(LocalDateTime time) {
		this.dtCreate = dtCreate;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDtUpdate() {
		return dtUpdate;
	}

	public void setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

}
