package pizza.api;

import java.time.LocalDateTime;

public interface IPizzaInfo {

	long getId();
	void setId(Long id);

	LocalDateTime getDtCreate();

	void setDtCreate(LocalDateTime dtCreate);

	LocalDateTime getDtUpdate();

	void setDtUpdate(LocalDateTime dtUpdate);

	String getName();

	String getDescription();

	int getSize();

	void setName(String name);

	void setDescription(String description);

	void setSize(int size);
}
