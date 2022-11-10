package pizza.service;

import java.time.LocalDateTime;
import java.util.List;
import pizza.api.IMenu;
import pizza.api.dto.MenuDTO;
import pizza.api.mapper.MenuMapper;
import pizza.api.validators.MenuValidator;
import pizza.dao.api.IMenuDao;
import pizza.service.api.IMenuService;

public class MenuService implements IMenuService {

	private final IMenuDao menuDao;
	private static MenuValidator menuValidator;

	public MenuService(IMenuDao menuDao) {
		this.menuDao = menuDao;
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
	public IMenu create(MenuDTO dto) {
		
		IMenu menu = MenuMapper.menuInputMapping(dto);
		menu.setDtCreate(LocalDateTime.now());
		menu.setDtUpdate(menu.getDtCreate());
		return MenuMapper.menuOutputMapping(this.menuDao.create(menu));
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