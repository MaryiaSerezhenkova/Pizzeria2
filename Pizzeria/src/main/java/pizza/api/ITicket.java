package pizza.api;

import java.time.LocalDateTime;


/**
 * Квиток выдаваемый к заказу
 */
public interface ITicket {


    /**
     * Уникальный номер заказа
     * @return
     */
    int getNumber();

    /**
     * Когда заказ получен
     * @return
     */
    LocalDateTime getCreatAt();

    /**
     * Заказ для которого выдали квиток
     * @return
     */
    IOrder getOrder();

	void setId(long id);
	long getId();

	void setDtCreate(LocalDateTime dtCreate);
	void setNumber (int number);
	void setOrder (long id);
}
