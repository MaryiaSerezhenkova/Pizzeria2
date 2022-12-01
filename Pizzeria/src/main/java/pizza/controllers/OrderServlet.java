package pizza.controllers;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import pizza.api.IOrder;
import pizza.api.core.JsonConverter;
import pizza.api.dto.OrderDTO;
import pizza.api.exceptions.ValidationException;
import pizza.api.mapper.ObjectMapperFactory;
import pizza.api.validators.IValidator;
import pizza.api.validators.OrderValidatorSingleton;
import pizza.service.OrderServiceSingleton;
import pizza.service.api.IOrderService;

@WebServlet(name = "OrderServlet", urlPatterns = "/order")
public class OrderServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final IOrderService orderService = OrderServiceSingleton.getInstance();
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private static final String PARAMETER_ID = "id";
	private static final String PARAMETER_VERSION = "dt_update";
	private final IValidator<OrderDTO> orderValidator = OrderValidatorSingleton.getInstance();
	private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();

	// Read POSITION
	// 1) Read list
	// 2) Read item (card) need id param
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType(CONTENT_TYPE);
		resp.setCharacterEncoding(ENCODING);
		String id = req.getParameter(PARAMETER_ID);
		try {

			if (id != null) {
				if (Long.valueOf(id) > 0) {
					resp.getWriter().write(mapper.writeValueAsString(orderService.read(Long.valueOf(id))));
					resp.setStatus(HttpServletResponse.SC_OK);
				} else {
					resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			} else {
				resp.getWriter()
						.write(mapper.registerModule(new JavaTimeModule()).writeValueAsString(orderService.get()));
				resp.setStatus(HttpServletResponse.SC_OK);
			}
		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	// CREATE POSITION
	// body json
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
		resp.setCharacterEncoding(ENCODING);
		resp.setContentType(CONTENT_TYPE);
		String jsonString = req.getReader().lines().collect(Collectors.joining());
		OrderDTO orderDto = mapper.readValue(jsonString, OrderDTO.class);
		try {
			orderValidator.validate(orderDto);
		} catch (ValidationException e) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}
		IOrder order = orderService.create(orderDto);
		resp.getWriter().write(mapper.writeValueAsString(order));
		resp.setStatus(HttpServletResponse.SC_CREATED);

	} catch (Exception e) {
		resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		System.err.println(e);
	}
}

	// UPDATE POSITION
	// need param id
	// need param version/date_update - optimistic lock
	// body json
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setCharacterEncoding(ENCODING);
			resp.setContentType(CONTENT_TYPE);
			String id = req.getParameter(PARAMETER_ID);
			String version = req.getParameter(PARAMETER_VERSION);
			String jsonString = req.getReader().lines().collect(Collectors.joining());

			if (id != null && version != null) {
				OrderDTO orderDto = mapper.readValue(jsonString, OrderDTO.class);
				try {
					orderValidator.validate(orderDto);
				} catch (ValidationException e) {
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
				IOrder order = orderService.update(Long.parseLong(id), JsonConverter.convert(Long.parseLong(version)),
						orderDto);
				resp.getWriter().write(mapper.registerModule(new JavaTimeModule()).writeValueAsString(order));
				resp.setStatus(HttpServletResponse.SC_CREATED);
			} else {
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}

	// DELETE POSITION
	// need param id
	// need param version/date_update - optimistic lock
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			resp.setCharacterEncoding(ENCODING);
			resp.setContentType(CONTENT_TYPE);
			String id = req.getParameter(PARAMETER_ID);
			String version = req.getParameter(PARAMETER_VERSION);
			if (id != null && version != null) {
				orderService.delete(Long.parseLong(id), JsonConverter.convert(Long.parseLong(version)));
				resp.setStatus(HttpServletResponse.SC_OK);
			} else {
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}