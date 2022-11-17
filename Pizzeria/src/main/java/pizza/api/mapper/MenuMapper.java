package pizza.api.mapper;

import pizza.api.IMenu;
import pizza.api.core.Menu;
import pizza.api.dto.MenuDTO;

public class MenuMapper {
    public static IMenu menuInputMapping(MenuDTO menu) {
        return new Menu(menu.getName(), menu.isEnabled(), menu.getItems());
    }

    public static Menu menuOutputMapping(IMenu menu) {
        return new Menu(menu.getId(), menu.getDtCreate(), menu.getDtUpdate(), menu.getName(), menu.isEnabled(), menu.getItems());
    }

}