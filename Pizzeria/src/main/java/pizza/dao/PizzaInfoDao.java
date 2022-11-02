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

import pizza.api.IPizzaInfo;
import pizza.api.core.PizzaInfo;
import pizza.dao.api.IPizzaInfoDao;

public class PizzaInfoDao implements IPizzaInfoDao {

	private static final String INSERT_SQL = "INSERT INTO app.pizza_info(\r\n"
			+ "	id, dt_create, dt_update, name, description, size)\r\n" + "	VALUES (?, ?, ?, ?, ?, ?);";

	private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, dt_update, name, description, size\r\n"
			+ "	FROM app.pizza_info WHERE id = ?;";

	private static final String SELECT_SQL = "SELECT id, dt_create, dt_update, name, description, size\r\n"
			+ "	FROM app.pizza_info;";

	private static final String UPDATE_SQL = "UPDATE app.pizza_info\r\n"
			+ "	SET id=?, dt_create=?, dt_update=?, name=?, description=?, size=?\r\n"
			+ "\tWHERE id = ? and dt_update = ?;";

	private static final String DELETE_SQL = "DELETE FROM app.pizza_info" + "\tWHERE id = ? and dt_update = ?;";

	private final DataSource ds;

	public PizzaInfoDao(DataSource instance) {
		this.ds = instance;
	}

	@Override
	public IPizzaInfo create(IPizzaInfo item) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
			stm.setObject(1, item.getDtCreate());
			stm.setObject(2, item.getDtUpdate());
			stm.setString(3, item.getName());
			stm.setString(4, item.getDescription());
			stm.setInt(5, item.getSize());
			int updated = stm.executeUpdate();
			return read(stm.getGeneratedKeys().getLong(1));
		} catch (SQLException e) {
			throw new RuntimeException("При сохранении данных произошла ошибка", e);
		}
	}

	@Override
	public IPizzaInfo read(long id) {
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
	public List<IPizzaInfo> get() {
		List<IPizzaInfo> pizzaArr = new ArrayList<>();
		try (Connection con = ds.getConnection();

				Statement stm = con.createStatement()) {
			ResultSet rs = stm.executeQuery(SELECT_SQL);

			while (rs.next()) {
				pizzaArr.add(mapper(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return pizzaArr;
	}

	@Override
	public IPizzaInfo update(long id, LocalDateTime dtUpdate, IPizzaInfo item) {
		try (Connection conn = ds.getConnection();
				PreparedStatement stm = conn.prepareStatement(UPDATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
			stm.setObject(1, item.getDtUpdate());
			stm.setString(2, item.getName());
			stm.setString(3, item.getDescription());
			stm.setInt(4, item.getSize());

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

	public IPizzaInfo mapper(ResultSet rs) throws SQLException {
		return new PizzaInfo(rs.getLong("id"), rs.getObject("dt_create", LocalDateTime.class),
				rs.getObject("dt_update", LocalDateTime.class), rs.getString("name"), rs.getString("description"),
				rs.getInt("size"));
	}


}
