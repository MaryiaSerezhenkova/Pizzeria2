package pizza.api.mapper;

import pizza.api.IMenuRow;
import pizza.api.core.MenuRow;
import pizza.api.dto.MenuRowDTO;

public class MenuRowMapper {
//	public static IMenuRow menuRowInputMapping(MenuRowDTO menuRowDTO) {
//		return new MenuRow(menuRowDTO.getPizzaInfoId(), menuRowDTO.getPrice(), menuRowDTO.getMenuId());
//	}

	public static MenuRow menuRowOutputMapping(IMenuRow menuRow) {
		return new MenuRow(menuRow.getInfo(), menuRow.getPrice());
	}
}