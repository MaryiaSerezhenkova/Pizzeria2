package pizza.api.validators;

import pizza.api.dto.MenuRowDTO;

public class MenuRowValidator implements IValidator<MenuRowDTO> {
	public void validate(MenuRowDTO menuRowDTO) {
		if ((Double) menuRowDTO.getPrice() == null || menuRowDTO.getPrice() <= 0) {
			throw new IllegalArgumentException("price is not valid");
		}
		if ((Long) menuRowDTO.getPizzaInfoId() == null || menuRowDTO.getPizzaInfoId() <= 0) {
			throw new IllegalArgumentException("pizza info id is not valid");
		}
		if ((Long) menuRowDTO.getMenuId() == null || menuRowDTO.getMenuId() <= 0) {
			throw new IllegalArgumentException("menu id price is not valid");
		}
	}
}