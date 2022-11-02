package pizza.service;
import java.time.LocalDateTime;
import java.util.List;
import pizza.api.IPizzaInfo;
import pizza.api.core.PizzaInfo;
import pizza.api.mapper.PizzaInfoMapper;
import pizza.dao.PizzaInfoDaoSingleton;
import pizza.dao.api.IPizzaInfoDao;
import pizza.service.api.IPizzaInfoService;

public class PizzaInfoService implements IPizzaInfoService {
	private final IPizzaInfoDao pizzaInfoDao;

	public PizzaInfoService(IPizzaInfoDao pizzaInfoDao) {
		this.pizzaInfoDao = pizzaInfoDao;
	}
	public PizzaInfoService() {
		this.pizzaInfoDao=PizzaInfoDaoSingleton.getInstance();
	}
	@Override
	public IPizzaInfo create(IPizzaInfo item) {
		item.setDtCreate(LocalDateTime.now());
		item.setDtUpdate(item.getDtCreate());
		return pizzaInfoDao.create(item);
	}

	@Override
	public IPizzaInfo read(long id) {
		return pizzaInfoDao.read(id);
	}

	@Override
	public List<IPizzaInfo> get() {
		return pizzaInfoDao.get();
	}
	 public PizzaInfo get(Long id) {
	        return PizzaInfoMapper.pizzaInfoOutputMapping(this.pizzaInfoDao.read(id));
	    }

	@Override
	public IPizzaInfo update(long id, LocalDateTime dtUpdate, IPizzaInfo item) {
		IPizzaInfo readed = pizzaInfoDao.read(id);

		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
		}

		readed.setDtUpdate(LocalDateTime.now());
		readed.setName(item.getName());
		readed.setDescription(item.getDescription());
		readed.setSize(item.getSize());
		
		return pizzaInfoDao.update(id, dtUpdate, readed);
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		IPizzaInfo readed = pizzaInfoDao.read(id);
		if (readed == null) {
			throw new IllegalArgumentException("Позиция не найдена");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению позиция уже была отредактирована кем-то другим");
		}

		pizzaInfoDao.delete(id, dtUpdate);
		
	}

}
