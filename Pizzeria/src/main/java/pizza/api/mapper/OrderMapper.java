package pizza.api.mapper;

import pizza.api.IOrder;
import pizza.api.core.Order;
import pizza.api.dto.OrderDTO;

public class OrderMapper {
	 public static IOrder orderInputMapping(OrderDTO order) {
	        return new Order(order.getSelectedItems());
	    }

	    public static Order orderOutputMapping(IOrder order) {
	        return new Order(order.getId(), order.getDtCreate(), order.getDtUpdate(), order.getSelected());
	    }

}
