package pizza.dao.api;


import java.time.LocalDateTime;
import java.util.List;

public interface IDao<TYPE> {
    TYPE create(TYPE item);
    TYPE read(long id);
    List<TYPE> get();
    TYPE update(long id, LocalDateTime dtUpdate, TYPE item);
    void delete(long id, LocalDateTime dtUpdate);
}
