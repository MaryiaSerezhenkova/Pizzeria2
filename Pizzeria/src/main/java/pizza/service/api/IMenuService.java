package pizza.service.api;

import pizza.api.IMenu;
import pizza.api.IMenuRow;
import pizza.api.dto.MenuDTO;

public interface IMenuService extends IService<IMenu, MenuDTO> {
	IMenuRow getRowById(long id);
}
