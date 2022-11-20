package pizza.dao;

import javax.sql.DataSource;

public class OrderDao {
	private static final String INSERT_SQL = "INSERT INTO app.\"order\"(\r\n" + "	dt_create, dt_update)\r\n"
			+ "	VALUES (?, ?);";
	private static final String INSERT_ROWS_SQL = "INSERT INTO app.selected_items(\r\n"
			+ "	\"row\", count, \"order\")\r\n" + "	VALUES (?, ?, ?);";
	
	private static final String SELECT_BY_ID_SQL = "SELECT id, dt_create, dt_update\n" + "\tFROM app.order\n"
			+ "\tWHERE id = ?;";
//	private static final String SELECT_ROWS_BY_ORDER_ID_SQL = "SELECT\n" + "    row.price AS row_price,\n"
//			+ "    info.id AS info_id,\n" + "    info.dt_create AS info_dt_create,\n"
//			+ "    info.dt_update AS info_dt_update,\n" + "    info.name AS info_name,\n"
//			+ "    info.description AS info_description,\n" + "    info.size AS info_size\n" + "FROM\n"
//			+ "    app.menu_rows AS row\n" + "    JOIN app.pizza_info AS info ON row.pizza = info.id\n"  + "WHERE\n"
//			+ "    order = ?;\n";
//
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
}
