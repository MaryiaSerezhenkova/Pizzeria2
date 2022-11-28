package pizza.api.dto;


public class StageDTO {
	
	private String description;

	public StageDTO() {
		super();
	}

	public StageDTO(String description) {
		super();
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
