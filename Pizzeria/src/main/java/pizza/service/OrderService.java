package pizza.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pizza.api.IOrder;
import pizza.api.ISelectedItem;
import pizza.api.core.Order;
import pizza.api.dto.OrderDTO;
import pizza.api.mapper.OrderMapper;
import pizza.api.validators.OrderValidator;
import pizza.dao.api.IOrderDao;
import pizza.service.api.IMenuService;
import pizza.service.api.IOrderService;

public class OrderService implements IOrderService {

	private final IOrderDao orderDao;
	private static OrderValidator orderValidator;
	private final IMenuService menuService;

	public OrderService(IOrderDao orderDao, IMenuService menuService) {
		this.orderDao = orderDao;
		this.menuService = menuService;
	}


	@Override
	public IOrder create(OrderDTO orderDto) {
		IOrder order = new Order();
		order.setDtCreate(LocalDateTime.now());
		order.setDtUpdate(order.getDtCreate());
		List<ISelectedItem> items = new ArrayList<>();
		for (ISelectedItem i: items) {
			items.add(i.getCount(), (ISelectedItem)this.menuService.getRowById(i.getRow().getInfo().getId()));
		}
		
//		order.setItems(orderDto.getSelectedItems().stream()
//				.map(i -> new SelectedItem(
//							this.menuService.getRowById(i.getMenuRow()), 
//							i.getCount())
//					).collect(Collectors.toList());
		
		return this.orderDao.create(order);
		
	}

	@Override
	public IOrder read(long id) {
		return OrderMapper.orderOutputMapping(this.orderDao.read(id));
	}

	@Override
	public List<IOrder> get() {
		return orderDao.get();
	}


	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		IOrder readed = orderDao.read(id);

		if (readed == null) {
			throw new IllegalArgumentException("Заказ не найден");
		}

		if (!readed.getDtUpdate().isEqual(dtUpdate)) {
			throw new IllegalArgumentException("К сожалению заказ уже был отредактирован кем-то другим");
		}

		orderDao.delete(id, dtUpdate);

	}


	@Override
	public IOrder update(long id, LocalDateTime dtUpdate, OrderDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
