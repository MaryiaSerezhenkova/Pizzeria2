package pizza.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import pizza.api.IOrder;
import pizza.api.ISelectedItem;
import pizza.api.core.Order;
import pizza.api.dto.OrderDTO;
import pizza.api.validators.OrderValidator;
import pizza.dao.api.IOrderDao;
import pizza.service.api.IMenuService;
import pizza.service.api.IOrderService;
import pizza.service.api.IPizzaInfoService;

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
		
		order.setItems(orderDto.getSelectedItems().stream()
				.map(i -> new SelectedItem(
							this.menuService.getRowById(i.getMenuRow()), 
							i.getCount()
						)
					).collect(Collectors.toList());
		
		return this.orderDao.create(order);
//		order.setItems(
//				orderDto.
//				getSelectedItems().stream()
//				.map(i -> new SelectedItem())
//				.collect(Collectors.toList())
//		);
	}

	@Override
	public IOrder read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IOrder> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IOrder update(long id, LocalDateTime dtUpdate, OrderDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub

	}

}
