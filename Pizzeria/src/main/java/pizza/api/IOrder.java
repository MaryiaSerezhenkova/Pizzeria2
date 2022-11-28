package pizza.api;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Заказ сформированный покупателем
 */
public interface IOrder {

	long getId();

	LocalDateTime getDtCreate();

	void setDtCreate(LocalDateTime dtCreate);

	LocalDateTime getDtUpdate();

	void setDtUpdate(LocalDateTime dtUpdate);

	/**
	 * Список выбранного для заказа
	 * 
	 * @return список выбранного
	 */
	List<ISelectedItem> getSelected();

	void setItems(List<ISelectedItem> items);
}
