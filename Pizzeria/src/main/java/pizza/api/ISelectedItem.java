package pizza.api;
/**
 * Выбор покупателя
 */
import java.time.LocalDateTime;

/**
 * Выбор покупателя
 */
public interface ISelectedItem {
	/**
	 * Выбранное из меню
	 * 
	 * @return
	 */
	IMenuRow getRow();

	/**
	 * Количество выбранного
	 * 
	 * @return
	 */
	int getCount();

	LocalDateTime getDtCreate();

	void setDtCreate(LocalDateTime dtCreate);

	LocalDateTime getDtUpdate();

	void setDtUpdate(LocalDateTime dtUpdate);

	long getId();

	void setId(Long id);
}