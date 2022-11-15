package pizza.api;

import java.time.LocalDateTime;
import java.util.List;

import pizza.api.core.MenuRow;

///**
// * Меню
// */
public interface IMenu {

	long getId();

	LocalDateTime getDtCreate();

	void setDtCreate(LocalDateTime dtCreate);

	LocalDateTime getDtUpdate();

	void setDtUpdate(LocalDateTime dtUpdate);

//    /**
//     * Получить название меню
//     * @return
//     */
	String getName();

	void setName(String name);

//    /**
//     * Доступные к заказу пункты
//     * @return пункты которые можно заказать
//     */
	List<IMenuRow> getItems();

	void getItems(List<IMenuRow> items);

//    /**
//     * Меню доступно к использованию для заказа?
//     * @return
//     */
	boolean isEnabled();

	void setEnabled(boolean enabled);

	void setId(long id);


	void setItems(List<IMenuRow> items);
}
