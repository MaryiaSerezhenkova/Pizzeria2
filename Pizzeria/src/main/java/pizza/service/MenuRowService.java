package pizza.service;
import java.time.LocalDateTime;
import java.util.List;

import pizza.api.IMenuRow;
import pizza.api.dto.MenuRowDTO;
import pizza.api.mapper.MenuRowMapper;
import pizza.dao.api.IMenuRowDao;
import pizza.service.api.IMenuRowService;

public class MenuRowService implements IMenuRowService {

	private final IMenuRowDao menuRowDao;

	public MenuRowService(IMenuRowDao menuRowDao) {
		this.menuRowDao = menuRowDao;
	}

	@Override
	public IMenuRow create(MenuRowDTO dto) {
		IMenuRow menuRow = MenuRowMapper.menuRowInputMapping(dto);
		menuRow.setDtCreate(LocalDateTime.now());
		menuRow.setDtUpdate(menuRow.getDtCreate());
		return MenuRowMapper.menuRowOutputMapping(this.menuRowDao.create(menuRow));
	}

	@Override
	public IMenuRow read(long id) {
		return MenuRowMapper.menuRowOutputMapping(this.menuRowDao.read(id));
	}

	@Override
	public List<IMenuRow> get() {
		return menuRowDao.get();
	}

	@Override
	public IMenuRow update(long id, LocalDateTime dtUpdate, MenuRowDTO item) {
		IMenuRow readed = menuRowDao.read(id);

		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
		}

		readed.setDtUpdate(LocalDateTime.now());
		readed.setPizzaInfoId(item.getPizzaInfoId());
		readed.setPrice(item.getPrice());
		readed.setMenuId(item.getMenuId());

		return menuRowDao.update(id, dtUpdate, readed);
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		IMenuRow readed = menuRowDao.read(id);
		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
		}

		menuRowDao.delete(id, dtUpdate);

	}

}