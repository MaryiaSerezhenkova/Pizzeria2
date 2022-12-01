package pizza.dao.api;

import pizza.api.IMenu;
import pizza.api.IMenuRow;

public interface IMenuDao extends IDao<IMenu> {

	IMenuRow readByRowId(long id);

}
