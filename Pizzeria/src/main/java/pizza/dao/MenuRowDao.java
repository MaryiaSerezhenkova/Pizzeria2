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

import pizza.api.IMenuRow;
import pizza.api.IPizzaInfo;
import pizza.api.core.MenuRow;
import pizza.api.core.PizzaInfo;
import pizza.dao.api.IMenuRowDao;

public class MenuRowDao implements IMenuRowDao {

	private static final String INSERT_SQL = "INSERT INTO app.menu_row(\r\n"
			+ "dt_create, dt_update, info, price, menu)\r\n" + "	VALUES (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, dt_update, info, price, menu\n"
			+ "	FROM app.menu_row;" + "\tWHERE id = ?;";

	private static final String SELECT_SQL = "SELECT id, dt_create, dt_update, info, price, menu\n"
			+ "	FROM app.menu_row;";

	private static final String UPDATE_SQL = "UPDATE app.menu_row\n" + "	SET dt_update=?, info=?, price=?, menu=?"
			+ "\tWHERE id = ? and dt_update < ?;";

	private static final String DELETE_SQL = "DELETE FROM app.menu_row" + "\tWHERE id = ? and dt_update = ?;";

	private final DataSource ds;

	public MenuRowDao(DataSource ds) {
		this.ds = ds;
	}

	public IMenuRow mapper(ResultSet rs) throws SQLException {
		return new MenuRow(rs.getLong("id"), rs.getObject("dt_create", LocalDateTime.class),
				rs.getObject("dt_update", LocalDateTime.class), rs.getLong("info"), rs.getDouble("price"),
				rs.getLong("menu"));
	}

//	private IMenuRow menuRowMapper(ResultSet rs) throws SQLException {
//		IPizzaInfo pizzaInfo = new PizzaInfo(rs.getLong("id"), rs.getObject("dt_create", LocalDateTime.class),
//				rs.getObject("dt_update", LocalDateTime.class), rs.getString("name"), rs.getString("description"),
//				rs.getInt("size"));
//		return new MenuRow(rs.getLong("id"), pizzaInfo, rs.getObject("dt_create", LocalDateTime.class),
//				rs.getObject("dt_update", LocalDateTime.class), rs.getLong("info"), rs.getDouble("price"),
//				rs.getLong("menu"));
//	}
//
//	@Override
//	public IMenuRow create(IMenuRow item) {
//		try (Connection conn = ds.getConnection();
//				PreparedStatement stm = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
//			stm.setObject(1, item.getDtCreate());
//			stm.setObject(2, item.getDtUpdate());
//			stm.setObject(3, item.getInfo());
//			stm.setDouble(4, item.getPrice());
//			stm.setObject(5, item.getMenu());
//			stm.executeUpdate();
//			ResultSet rs = stm.getGeneratedKeys();
//			if (rs.next()) {
//				item.setId(rs.getLong(1));
//			}
//			return item;
//		} catch (SQLException e) {
//			throw new RuntimeException("При сохранении данных произошла ошибка", e);
//		}
//
//	}

	@Override
	public IMenuRow read(long id) {
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
	public IMenuRow update(long id, LocalDateTime dtUpdate, IMenuRow item) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
		//	stm.setObject(1, item.getDtUpdate());
			//stm.setLong(2, item.getPizzaInfoId());
			stm.setDouble(3, item.getPrice());
			//stm.setLong(4, item.getMenuId());

			stm.setLong(5, id);
			stm.setObject(6, dtUpdate);

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

	@Override
	public List<IMenuRow> get() {
		List<IMenuRow> menuRow = new ArrayList<>();
		try (Connection con = ds.getConnection();

				Statement stm = con.createStatement()) {
			ResultSet rs = stm.executeQuery(SELECT_SQL);

			while (rs.next()) {
				menuRow.add(mapper(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return menuRow;
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

	@Override
	public IMenuRow create(IMenuRow item) {
		// TODO Auto-generated method stub
		return null;
	}
}