package pizza.api;
import java.time.LocalTime;

/**
 * Описание этапа выполнения заказа
 */
public interface IStage {
    /**
     * Описание этапа
     * @return
     */
    String getDescription();

    /**
     * Когда этап был начат
     * @return
     */
    LocalTime getTime();
}
