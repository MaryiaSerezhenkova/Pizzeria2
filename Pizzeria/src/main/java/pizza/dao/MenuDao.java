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
import pizza.api.core.Menu;
import pizza.dao.api.IMenuDao;

public class MenuDao implements IMenuDao {
	private static final String INSERT_SQL = "INSERT INTO app.menu(\n" + "\tdt_create, dt_update, name, enable)\n"
			+ "\tVALUES (?, ?, ?, ?);";

	private static final String INSERT_ROWS_SQL = "INSERT INTO app.menu_rows(pizza, price, menu)\n"
			+ "	VALUES (?, ?, ?);";
	private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, dt_update, name, enable\n"
			+ "\tFROM app.menu\n" + "\tWHERE id = ?;";
	private static final String SELECT_ROWS_BY_MENU_ID_SQL = "SELECT\n" + "    row.price AS row_price,\n"
			+ "    info.id AS info_id,\n" + "    info.dt_create AS info_dt_create,\n"
			+ "    info.dt_update AS info_dt_update,\n" + "    info.name AS info_name,\n"
			+ "    info.description AS info_description,\n" + "    info.size AS info_size\n" + "FROM\n"
			+ "    app.menu_rows AS row\n" + "    JOIN app.pizza_info AS info ON row.pizza = info.id\n" + "WHERE\n"
			+ "    menu = ?;\n";

	private static final String SELECT_SQL = "SELECT id, dt_create, dt_update, name, enable\n" + "\tFROM app.menu;";

	private static final String UPDATE_SQL = "UPDATE app.menu\n" + "\tSET dt_update = ?, name = ?, enable = ?\n"
			+ "\tWHERE id = ? and dt_update = ?;";

	private static final String DELETE_SQL = "DELETE FROM app.menu\n" + "\tWHERE id = ? and dt_update = ?;";

	private final DataSource ds;

	public MenuDao(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<IMenu> get() {
		List<IMenu> data = new ArrayList<>();
		try (Connection conn = ds.getConnection(); PreparedStatement stm = conn.prepareStatement(SELECT_SQL);) {
			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {
					data.add(mapper(rs));
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}

		return data;
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(DELETE_SQL, Statement.RETURN_GENERATED_KEYS)) {
			stm.setLong(1, id);
			stm.setObject(2, dtUpdate);

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

	public IMenu mapper(ResultSet rs) throws SQLException {
		return new Menu(rs.getLong("id"), rs.getObject("dt_create", LocalDateTime.class),
				rs.getObject("dt_update", LocalDateTime.class), rs.getString("name"), rs.getBoolean("enable"));
	}

	public IMenu create(IMenu item) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS);
				PreparedStatement stmRows = conn.prepareStatement(INSERT_ROWS_SQL);) {
			stm.setObject(1, item.getDtCreate());
			stm.setObject(2, item.getDtUpdate());
			stm.setString(3, item.getName());
			stm.setBoolean(4, item.isEnabled());
			stm.executeUpdate();
			try (ResultSet rs = stm.getGeneratedKeys();) {

				while (rs.next()) {
					long menuId = rs.getLong(1);
					for (IMenuRow row : item.getItems()) {
						stmRows.setLong(1, row.getInfo().getId());
						stmRows.setDouble(2, row.getPrice());
						// stmRows.setDouble(3, menuId);

						stmRows.addBatch();
					}

					stmRows.executeBatch();

					return read(menuId);
				}

				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	public IMenu read(long id) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(SELECT_BY_ID_SQL);
				PreparedStatement stmRows = conn.prepareStatement(SELECT_ROWS_BY_MENU_ID_SQL)) {
			stm.setObject(1, id);
			// stm.setLong(1, id);
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

	private IMenu mapper(ResultSet rs, ResultSet rsRows) {
		// TODO Auto-generated method stub
		return null;
	}

	public IMenu update(long id, LocalDateTime dtUpdate, IMenu item) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
			stm.setObject(1, item.getDtUpdate());
			stm.setString(2, item.getName());
			stm.setBoolean(3, item.isEnabled());

			stm.setLong(4, id);
			stm.setObject(5, dtUpdate);

			int countUpdatedRows = stm.executeUpdate();

			if (countUpdatedRows != 1) {
				if (countUpdatedRows == 0) {
					throw new IllegalArgumentException("Не смогли обновить какую либо запись");
				} else {
					throw new IllegalArgumentException("Обновили более одной записи");
				}
			}

			return read(id);
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

}