package pizza.service;

import java.time.LocalDateTime;
import java.util.List;
import pizza.api.IMenu;
import pizza.dao.api.IMenuDao;
import pizza.service.api.IMenuService;

public class MenuService implements IMenuService {

	private final IMenuDao menuDao;

	public MenuService(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public List<IMenu> get() {
		return menuDao.get();
	}

	public IMenu create(IMenu item) {

		item.setDtCreate(LocalDateTime.now());
		item.setDtUpdate(item.getDtCreate());

		return menuDao.create(item);
	}

	public IMenu read(long id) {
		return menuDao.read(id);
	}

	public IMenu update(long id, LocalDateTime dtUpdate, IMenu item) {
		IMenu readed = menuDao.read(id);

		if (readed == null) {
			throw new IllegalArgumentException("Меню не найдено");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению меню уже было отредактировано кем-то другим");
		}

		readed.setDtUpdate(LocalDateTime.now());

		readed.setName(item.getName());
		readed.setEnabled(item.isEnabled());

		return menuDao.update(id, dtUpdate, readed);
	}

	public void delete(long id, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub

	}
}
