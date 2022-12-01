package pizza.dao;

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
import pizza.api.ITicket;
import pizza.api.core.Ticket;
import pizza.dao.api.ITicketDao;

public class TicketDao implements ITicketDao {

	private final DataSource ds;

	public TicketDao(DataSource ds) {
		this.ds = ds;
	}

	private static final String INSERT_SQL = "INSERT INTO app.ticket(\r\n" + "	dt_create, \"number\", \"order\")\r\n"
			+ "	VALUES (?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, \"number\", \"order\"\r\n"
			+ "	FROM app.ticket" + "\tWHERE id = ?;";

	private static final String SELECT_SQL = "SELECT id, dt_create, \"number\", \"order\"\r\n" + "	FROM app.ticket";

	private static final String SELECT_BY_ORDER_ID_SQL = "SELECT ticket.id, ticket.dt_create, \"number\", \"order\"\r\n"
			+ "	FROM app.ticket AS ticket\r\n" + "	JOIN app.order AS ord ON ticket.order=ord.id\r\n"
			+ "	WHERE ticket.order=?;";
	private static final String DELETE_SQL = "DELETE FROM app.ticket" + "\tWHERE id = ? and dt_update = ?;";

	@Override
	public ITicket create(ITicket item) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
			stm.setObject(1, item.getCreatAt());
			stm.setObject(2, item.getNumber());
			stm.setObject(3, item.getOrder());
			stm.executeUpdate();
			ResultSet rs = stm.getGeneratedKeys();
			if (rs.next()) {
				item.setId(rs.getLong(1));
			}
			return item;
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	@Override
	public ITicket read(long id) {
		try (Connection conn = ds.getConnection(); PreparedStatement stm = conn.prepareStatement(SELECT_BY_ID_SQL)) {
			stm.setObject(1, id);

			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					return mapper(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}

		return null;
	}

	@Override
	public List<ITicket> get() {
		List<ITicket> tickets = new ArrayList<>();
		try (Connection con = ds.getConnection();

				Statement stm = con.createStatement()) {
			ResultSet rs = stm.executeQuery(SELECT_SQL);

			while (rs.next()) {
				tickets.add(mapper(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tickets;
	}

	@Override
	public ITicket update(long id, LocalDateTime dtUpdate, ITicket type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(DELETE_SQL, Statement.RETURN_GENERATED_KEYS)) {
			stm.setLong(1, id);

			int countUpdatedRows = stm.executeUpdate();

			if (countUpdatedRows != 1) {
				if (countUpdatedRows == 0) {
					throw new IllegalArgumentException("Не смогли удалить какую либо запись");
				} else {
					throw new IllegalArgumentException("Удалили более одной записи");
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	public ITicket mapper(ResultSet rs) throws SQLException {
		return new Ticket(rs.getLong("id"), rs.getObject("dt_create", LocalDateTime.class), rs.getInt("number"),
				(IOrder) rs.getObject("order"));
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}
	
	public ITicket readByOrderId(long orderId) {
		try (Connection conn = ds.getConnection(); PreparedStatement stm = conn.prepareStatement(SELECT_BY_ORDER_ID_SQL)) {
			stm.setObject(1, orderId);

			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					return mapper(rs);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}

		return null;
	}
}
