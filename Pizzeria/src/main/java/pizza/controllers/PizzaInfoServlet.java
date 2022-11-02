package pizza.controllers;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pizza.api.IPizzaInfo;
import pizza.api.core.JsonConverter;
import pizza.service.PizzaInfoServiceSingleton;
import pizza.service.api.IPizzaInfoService;

@WebServlet(name = "PizzaInfoServlet", urlPatterns = "/pizza-info")
public class PizzaInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final IPizzaInfoService pizzaInfoService = PizzaInfoServiceSingleton.getInstance();
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private static final String PARAMETER_ID = "id";

	// Read POSITION
	// 1) Read list
	// 2) Read item (card) need id param
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//List<IPizzaInfo> pizzaInfo = pizzaInfoService.get();
		try {
			resp.setContentType(CONTENT_TYPE);
			resp.setCharacterEncoding(ENCODING);
			String id = req.getParameter(PARAMETER_ID);
			if (id != null) {
		//		resp.getWriter().write(JsonConverter.fromPizzaInfoToJson(pizzaInfoService.read(Long.valueOf(id))));
			} else {
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
//	            } else {
//	                resp.getWriter().write(JsonConverter.fromPizzaInfoListToJson(pizzaInfoService.get()));
//	            }
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}

		resp.setStatus(HttpServletResponse.SC_OK);
	}

	// CREATE POSITION
	// body json
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		super.doPost(req, resp);
	}

	// UPDATE POSITION
	// need param id
	// need param version/date_update - optimistic lock
	// body json
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPut(req, resp);
	}

	// DELETE POSITION
	// need param id
	// need param version/date_update - optimistic lock
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doDelete(req, resp);
	}
}