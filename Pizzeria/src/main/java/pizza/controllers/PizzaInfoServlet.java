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

import pizza.api.IPizzaInfo;
import pizza.api.core.JsonConverter;
import pizza.api.dto.PizzaInfoDto;
import pizza.api.exceptions.ValidationException;
import pizza.api.mapper.ObjectMapperFactory;
import pizza.api.validators.IValidator;
import pizza.api.validators.PizzaInfoValidatorSingleton;
import pizza.service.PizzaInfoServiceSingleton;
import pizza.service.api.IPizzaInfoService;

@WebServlet(name = "PizzaInfoServlet", urlPatterns = "/pizza-info")
public class PizzaInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private final IPizzaInfoService pizzaInfoService = PizzaInfoServiceSingleton.getInstance();
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private static final String PARAMETER_ID = "id";
	private static final String PARAMETER_VERSION = "dt_update";
	private final IValidator<PizzaInfoDto> pizzaInfoValidator= PizzaInfoValidatorSingleton.getInstance() ;
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
					resp.getWriter().write(mapper.writeValueAsString(pizzaInfoService.read(Long.valueOf(id))));
					resp.setStatus(HttpServletResponse.SC_OK);
				} else {
					resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			} else {
				resp.getWriter()
						.write(mapper.registerModule(new JavaTimeModule()).writeValueAsString(pizzaInfoService.get()));
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
			PizzaInfoDto pizzaInfo = mapper.readValue(jsonString, PizzaInfoDto.class);
			try {
				pizzaInfoValidator.validate(pizzaInfo);
			} catch (ValidationException e) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			IPizzaInfo pizzaInfoDto = pizzaInfoService.create(pizzaInfo);
			resp.getWriter().write(mapper.writeValueAsString(pizzaInfoDto));
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
				PizzaInfoDto pizzaInfo = mapper.readValue(jsonString, PizzaInfoDto.class);
				try {
					pizzaInfoValidator.validate(pizzaInfo);
				} catch (ValidationException e) {
					resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
				IPizzaInfo pizzaInfoDto = pizzaInfoService.update(Long.parseLong(id),
						JsonConverter.convert(Long.parseLong(version)), pizzaInfo);
				resp.getWriter().write(mapper.registerModule(new JavaTimeModule()).writeValueAsString(pizzaInfoDto));
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
				pizzaInfoService.delete(Long.parseLong(id), JsonConverter.convert(Long.parseLong(version)));
				resp.setStatus(HttpServletResponse.SC_OK);
			} else {
				resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	}
}