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
import pizza.api.core.MenuRow;
import pizza.api.core.PizzaInfo;
import pizza.dao.api.IMenuDao;

public class MenuDao implements IMenuDao {
	private static final String INSERT_SQL = "INSERT INTO app.menu(\n" + "\tdt_create, dt_update, name, enable)\n"
			+ "\tVALUES (?, ?, ?, ?);";

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

	private static final String INSERT_ROWS_SQL = "INSERT INTO app.menu_rows(\n" + "\tpizza, price, menu)\n"
			+ "\tVALUES (?, ?, ?);";

	private static final String DELETE_ROWS_SQL = "DELETE FROM app.menu_rows WHERE menu = ?;";
	private final DataSource ds;
	private static final String SELECT_ROW_BY_ID_SQL = "SELECT info.id, info.dt_create, info.dt_update, info.name, info.description, info.size, \n"
			+ "rows.price, items.count\n"
			+ "	FROM app.selected_items AS items\n"
			+ "	JOIN app.order AS ord ON items.order=ord.id\n"
			+ "	JOIN app.menu_rows AS rows ON items.row=rows.id\n"
			+ "	JOIN app.pizza_info AS info ON rows.pizza=info.id\n"
			+ "	JOIN app.menu AS men ON men.id=rows.menu\n"
			+ "	WHERE rows.id=?;";

	public MenuDao(DataSource ds) {
		this.ds = ds;
	}

	@Override
	public List<IMenu> get() {
		List<IMenu> data = new ArrayList<>();
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(SELECT_SQL);
				PreparedStatement stmRows = conn.prepareStatement(SELECT_ROWS_BY_MENU_ID_SQL);) {
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

//	public IMenu mapper(ResultSet rs) throws SQLException {
//		return new Menu(rs.getLong("id"), rs.getObject("dt_create", LocalDateTime.class),
//				rs.getObject("dt_update", LocalDateTime.class), rs.getString("name"), rs.getBoolean("enable"));
//	}

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
						stmRows.setDouble(3, menuId);

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
			// stm.setObject(1, id);
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

	public IMenu update(long id, LocalDateTime dtUpdate, IMenu item) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(UPDATE_SQL);
				PreparedStatement stmRowsDel = conn.prepareStatement(DELETE_ROWS_SQL);
				PreparedStatement stmRowsIns = conn.prepareStatement(INSERT_ROWS_SQL);) {
			conn.setAutoCommit(false);
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

			stmRowsDel.setLong(1, item.getId());

			stmRowsDel.executeUpdate();

			for (IMenuRow row : item.getItems()) {
				stmRowsIns.setLong(1, row.getInfo().getId());
				stmRowsIns.setDouble(2, row.getPrice());
				stmRowsIns.setDouble(3, item.getId());

				stmRowsIns.addBatch();
			}

			stmRowsIns.executeBatch();
			conn.commit();
			return read(id);
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	@Override
	public void delete(long id, LocalDateTime dtUpdate) {
		 try (Connection conn = ds.getConnection();
	             PreparedStatement stm = conn.prepareStatement(DELETE_SQL, Statement.RETURN_GENERATED_KEYS)
	        ){
	            stm.setLong(1, id);
	            stm.setObject(2, dtUpdate);

	            int countUpdatedRows = stm.executeUpdate();

	            if(countUpdatedRows != 1){
	                if(countUpdatedRows == 0){
	                    throw new IllegalArgumentException("Не смогли удалить какую либо запись");
	                } else {
	                    throw new IllegalArgumentException("Удалили более одной записи");
	                }
	            }
	        } catch (SQLException e){
	            throw new RuntimeException("При сохранении данных произошла ошибка", e);
	        }
	    }

	public IMenu mapper(ResultSet rs, ResultSet rsRows) throws SQLException {
		IMenu menu = new Menu(rs.getLong(1), rs.getObject(2, LocalDateTime.class), rs.getObject(3, LocalDateTime.class),
				rs.getString(4), rs.getBoolean(5));

		List<IMenuRow> rows = new ArrayList<>();
		while (rsRows.next()) {
			MenuRow row = new MenuRow();

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

		menu.setItems(rows);

		return menu;
	}
	public IMenuRow rowMapper(ResultSet rsRows) throws SQLException {
		IMenuRow row = new MenuRow();
		while (rsRows.next()) {
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

		return row;
	}

	public IMenuRow readByRowId(long id) {
		try (Connection conn = ds.getConnection();

				PreparedStatement stm = conn.prepareStatement(SELECT_ROW_BY_ID_SQL)) {
			stm.setLong(1, id);
			try (ResultSet rs = stm.executeQuery()) {
				while (rs.next()) {

					return rowMapper(rs);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
		return null;
	}
}