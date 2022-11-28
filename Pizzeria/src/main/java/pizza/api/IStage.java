package pizza.api;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Описание этапа выполнения заказа
 */
public interface IStage {
	/**
	 * Описание этапа
	 * 
	 * @return
	 */
	String getDescription();

	void setDescription(String description);

	/**
	 * Когда этап был начат
	 * 
	 * @return
	 */
	LocalDateTime getTime();

	void setTime(LocalDateTime time);
}
