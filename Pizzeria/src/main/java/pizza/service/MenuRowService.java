package pizza.service;
import java.time.LocalDateTime;
import java.util.List;

import pizza.api.IMenuRow;
import pizza.api.IPizzaInfo;
import pizza.api.core.PizzaInfo;
import pizza.api.dto.MenuRowDTO;
import pizza.api.dto.PizzaInfoDto;
import pizza.api.mapper.PizzaInfoMapper;
import pizza.dao.api.IMenuRowDao;
import pizza.dao.api.IPizzaInfoDao;
import pizza.service.api.IMenuRowService;

public class MenuRowService implements IMenuRowService {
	
	private final IMenuRowDao menuRowDao;
	
	public MenuRowService(IMenuRowDao menuRowDao) {
		this.menuRowDao=menuRowDao;
	}

	

//	public PizzaInfo get(Long id) {
//		return PizzaInfoMapper.pizzaInfoOutputMapping(this.pizzaInfoDao.read(id));
//	}
//
//	@Override
//	public IPizzaInfo update(long id, LocalDateTime dtUpdate, PizzaInfoDto item) {
//		IPizzaInfo readed = pizzaInfoDao.read(id);
//
//		if (readed == null) {
//			throw new IllegalArgumentException("Позиция не найдена");
//		}
//
//		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
//			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
//		}
//
//		readed.setDtUpdate(LocalDateTime.now());
//		readed.setName(item.getName());
//		readed.setDescription(item.getDescription());
//		readed.setSize(item.getSize());
//
//		return pizzaInfoDao.update(id, dtUpdate, readed);
//	}
//
//	@Override
//	public void delete(long id, LocalDateTime dtUpdate) {
//		IPizzaInfo readed = pizzaInfoDao.read(id);
//		if (readed == null) {
//			throw new IllegalArgumentException("Позиция не найдена");
//		}
//
//		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
//			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
//		}
//
//		pizzaInfoDao.delete(id, dtUpdate);
//
//	}
//
	@Override
	public IMenuRow create(MenuRowDTO dto) {
		return null;
//		IMenuRow menuRow = MenuRowMapper.pizzaInfoInputMapping(dto);
//		pizzaInfo.setDtCreate(LocalDateTime.now());
//		pizzaInfo.setDtUpdate(pizzaInfo.getDtCreate());
//		return PizzaInfoMapper.pizzaInfoOutputMapping(this.pizzaInfoDao.create(pizzaInfo));
	}

	@Override
	public IMenuRow read(long id) {
		return menuRowDao.read(id);
	}

	@Override
	public List<IMenuRow> get() {
		return menuRowDao.get();
	}

	@Override
	public IMenuRow update(long id, LocalDateTime dtUpdate, MenuRowDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}

}