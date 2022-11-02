package pizza.api.builders;

import java.time.LocalDateTime;

import pizza.api.core.PizzaInfo;

public class PizzaInfoBuilder {
	private long id;
	private LocalDateTime dtCreate;
	private LocalDateTime dtUpdate;
	private String name;
	private String description;
	private int size;

    private PizzaInfoBuilder() {
    }

    public static PizzaInfoBuilder create() {
        return new PizzaInfoBuilder();
    }

    public PizzaInfoBuilder setId(Long id) {
        this.id = id;
        return this;
    }

	public  PizzaInfoBuilder setDtCreate(LocalDateTime dtCreate) {
		this.dtCreate = dtCreate;
		return this;
	}


	public  PizzaInfoBuilder setDtUpdate(LocalDateTime dtUpdate) {
		this.dtUpdate = dtUpdate;
		return this;
	}


	public  PizzaInfoBuilder setName(String name) {
		this.name = name;
		return this;
	}
	

	public  PizzaInfoBuilder setDescription(String description) {
		this.description = description;
		return this;
	}

	 public PizzaInfoBuilder setSize(int size) {
	        this.size = size;
	        return this;
	    }

	public PizzaInfo build() {
        return new PizzaInfo (id, dtCreate,dtUpdate, name, description, size);
    }
}