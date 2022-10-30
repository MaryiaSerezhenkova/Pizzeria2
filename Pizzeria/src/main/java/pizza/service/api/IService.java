package pizza.service.api;


import java.time.LocalDateTime;
import java.util.List;

public interface IService<TYPE> {
    TYPE create(TYPE item);
    TYPE read(long id);
    List<TYPE> get();
    TYPE update(long id, LocalDateTime dtUpdate, TYPE item);
    void delete(long id, LocalDateTime dtUpdate);
}
