package pizza.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import pizza.api.IMenu;
import pizza.api.core.Menu;
import pizza.api.core.MenuRow;
import pizza.api.dto.MenuDTO;
import pizza.api.mapper.MenuMapper;
import pizza.api.validators.MenuValidator;
import pizza.dao.api.IMenuDao;
import pizza.service.api.IMenuService;
import pizza.service.api.IPizzaInfoService;

public class MenuService implements IMenuService {

	private final IMenuDao menuDao;
	private static MenuValidator menuValidator;
	private final IPizzaInfoService pizzaInfoService;
	
	public MenuService(IMenuDao menuDao, IPizzaInfoService pizzaInfoService) {
		this.menuDao = menuDao;
		this.pizzaInfoService = pizzaInfoService;
	}

	public List<IMenu> get() {
		return menuDao.get();
	}
	

	public IMenu read(long id) {
		return MenuMapper.menuOutputMapping(this.menuDao.read(id));
	}

	public void delete(long id, LocalDateTime dtUpdate) {
		IMenu readed = menuDao.read(id);

		if (readed == null) {
			throw new IllegalArgumentException("Меню не найдено");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
		}

		menuDao.delete(id, dtUpdate);

	}

	@Override
	public IMenu create(MenuDTO item) {
		IMenu menu = new Menu();
        menu.setDtCreate(LocalDateTime.now());
        menu.setDtUpdate(menu.getDtCreate());
        menu.setName(item.getName());
        menu.setEnabled(item.isEnabled());

        menu.setItems(item.getItems().stream()
                .map(i ->
                        new MenuRow(this.pizzaInfoService.read(i.getPizzaInfo()),
                                i.getPrice()))
                .collect(Collectors.toList()));


        return this.menuDao.create(menu);
	}

	@Override
	public IMenu update(long id, LocalDateTime dtUpdate, MenuDTO dto) {
		IMenu readed = menuDao.read(id);

		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
		}

		readed.setDtUpdate(LocalDateTime.now());
		readed.setName(dto.getName());
		readed.setEnabled(dto.isEnabled());

		return menuDao.update(id, dtUpdate, readed);
	}
}