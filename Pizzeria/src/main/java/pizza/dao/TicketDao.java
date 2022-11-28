package pizza.dao;

import java.awt.MenuItem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import pizza.api.IOrder;
import pizza.api.ISelectedItem;
import pizza.api.ITicket;
import pizza.api.core.Order;
import pizza.api.core.PizzaInfo;
import pizza.api.core.SelectedItem;
import pizza.api.core.Ticket;
import pizza.api.dto.TicketDTO;
import pizza.service.api.ITicketDao;

public class TicketDao implements ITicketDao {

    private static final String INSERT_TICKET_SQL = "INSERT INTO pizza_manager.ticket (order_id) VALUES (?);";
    private static final String SELECT_TICKET_SQL = "SELECT id, order_id, creation_date, version " +
            "FROM pizza_manager.ticket ORDER BY id;";
    private static final String SELECT_TICKET_BY_ID_SQL = "SELECT id, order_id,  creation_date, version " +
            "FROM pizza_manager.ticket WHERE id=?;";
    private static final String SELECT_TICKET_ALL_DATA_SQL = "SELECT ord.id AS id, ord.creation_date AS cd, " +
            "ord.version AS ver, t.id AS tid, t.creation_date AS tcd, t.version AS tver,si.id AS siid, menu_item_id, " +
            "count ,si.creation_date AS sicd, si.version AS siiv, mi.id AS miid, price, pizza_info_id, mi.creation_date AS micd, " +
            "mi.version AS miver, mi.menu_id AS meid, name, description, size, pi.creation_date AS picd, pi.version AS piv " +
            "FROM pizza_manager.order_table AS ord INNER JOIN pizza_manager.ticket t on ord.id = t.order_id " +
            "INNER JOIN pizza_manager.selected_item si on ord.id = si.order_id " +
            "INNER JOIN pizza_manager.menu_item mi on mi.id = si.menu_item_id " +
            "INNER JOIN pizza_manager.pizza_info pi on pi.id = mi.pizza_info_id WHERE ord.id=? ORDER BY siid, miid, pizza_info_id;";
    private static final String DELETE_TICKET_SQL = "DELETE FROM pizza_manager.ticket WHERE id=?;";
    private final DataSource dataSource;

    public TicketDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	@Override
	public ITicket create(TicketDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITicket read(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ITicket> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITicket update(long id, LocalDateTime dtUpdate, TicketDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}


}