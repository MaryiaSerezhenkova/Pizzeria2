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

import pizza.api.IMenu;
import pizza.api.IMenuRow;
import pizza.api.IOrder;
import pizza.api.ISelectedItem;
import pizza.api.core.MenuRow;
import pizza.api.core.Order;
import pizza.api.core.PizzaInfo;
import pizza.api.core.SelectedItem;
import pizza.dao.api.IOrderDao;

public class OrderDao implements IOrderDao {
	private static final String INSERT_SQL = "INSERT INTO app.\"order\"(\r\n" + "	dt_create, dt_update)\r\n"
			+ "	VALUES (?, ?);";
	private static final String INSERT_ROWS_SQL = "INSERT INTO app.selected_items(\r\n"
			+ "	\"row\", count, \"order\")\r\n" + "	VALUES (?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, dt_update\n" + "\tFROM app.order\n"
			+ "\tWHERE id = ?;";
	private static final String SELECT_ROWS_BY_ORDER_ID_SQL = "SELECT items.count" + "row.price AS row_price,\n"
			+ "    info.id AS info_id,\n" + "    info.dt_create AS info_dt_create,\n"
			+ "    info.dt_update AS info_dt_update,\n" + "    info.name AS info_name,\n"
			+ "    info.description AS info_description,\n" + "    info.size AS info_size\n"
			+ "    FROM app.selected_items AS items\n" + "	JOIN app.menu_rows AS row\n" + "	ON items.row=row.id\""
			+ "    JOIN app.pizza_info AS info ON row.pizza = info.id\n" + "WHERE\n" + "order = ?;\n";

	private static final String SELECT_SQL = "SELECT id, dt_create, dt_update\r\n" + "	FROM app.\"order\"";
//
	private static final String UPDATE_SQL = "UPDATE app.\"order\"\r\n" + "	SET dt_update=?"
			+ "\tWHERE id = ? and dt_update = ?;";

	private static final String DELETE_SQL = "DELETE FROM app.\"order\"" + "\tWHERE id = ? and dt_update = ?;";

	private static final String DELETE_ROWS_SQL = "DELETE FROM app.selected_items WHERE order = ?;";

	private final DataSource ds;

	public OrderDao(DataSource ds) {
		this.ds = ds;
	}

	public IOrder create(IOrder order) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement stmRows = conn.prepareStatement(INSERT_ROWS_SQL);) {
			stm.setObject(1, order.getDtCreate());
			stm.setObject(2, order.getDtUpdate());
			stm.executeUpdate();
			try (ResultSet rs = stm.getGeneratedKeys();) {
				while (rs.next()) {
					long orderId = rs.getLong(1);
					for (ISelectedItem item : order.getSelected()) {
						stmRows.setLong(1, item.getRow().getInfo().getId());
						stmRows.setInt(2, item.getCount());
						stmRows.setDouble(3, orderId);

						stmRows.addBatch();
					}

					stmRows.executeBatch();

					return read(orderId);
				}

				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	public IOrder read(long id) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(SELECT_BY_ID_SQL);
				PreparedStatement stmRows = conn.prepareStatement(SELECT_ROWS_BY_ORDER_ID_SQL)) {
			stm.setLong(1, id);
			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					stmRows.setLong(1, id);
					try (ResultSet rsRows = stmRows.executeQuery()) {
						return mapper(rs, rsRows);
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
		return null;
	}

	public IOrder mapper(ResultSet rs, ResultSet rsRows) throws SQLException {
		IOrder order = new Order(rs.getLong(1), rs.getObject(2, LocalDateTime.class),
				rs.getObject(3, LocalDateTime.class));

		List<ISelectedItem> items = new ArrayList<>();
		while (rsRows.next()) {
			SelectedItem item = new SelectedItem();
			items.add(item);
			MenuRow row = new MenuRow();
			item.setCount(rs.getInt("count"));
			item.setSelectedItem(row);
			List<IMenuRow> rows = new ArrayList<>();

			rows.add(row);
			PizzaInfo info = new PizzaInfo();
			row.setPrice(rsRows.getDouble("row_price"));
			row.setPizzaInfo(info);
			info.setId(rsRows.getLong("info_id"));
			info.setDtCreate(rsRows.getObject("info_dt_create", LocalDateTime.class));
			info.setDtUpdate(rsRows.getObject("info_dt_update", LocalDateTime.class));
			info.setName(rsRows.getString("info_name"));
			info.setDescription(rsRows.getString("info_description"));
			info.setSize(rsRows.getInt("info_size"));
		}

		order.setItems(items);

		return order;
	}

	@Override
	public List<IOrder> get() {
		List<IOrder> data = new ArrayList<>();
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(SELECT_SQL);
				PreparedStatement stmRows = conn.prepareStatement(SELECT_ROWS_BY_ORDER_ID_SQL);) {
			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					long id = rs.getLong(1);
					stmRows.setLong(1, id);
					try (ResultSet rsRows = stmRows.executeQuery()) {
						data.add(mapper(rs, rsRows));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}

		return data;
	}
	

	@Override
	public IOrder update(long id, LocalDateTime dtUpdate, IOrder type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		// TODO Auto-generated method stub
		
	}
}
