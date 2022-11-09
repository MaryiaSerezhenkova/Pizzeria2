package pizza.service.api;


import java.time.LocalDateTime;
import java.util.List;

public interface IService<TYPE, DTO> {
    TYPE create(DTO dto);
    TYPE read(long id);
    List<TYPE> get();
    TYPE update(long id, LocalDateTime dtUpdate, DTO dto);
    void delete(long id, LocalDateTime dtUpdate);
}
