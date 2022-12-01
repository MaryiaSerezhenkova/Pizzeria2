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

import pizza.api.ITicket;
import pizza.api.dto.TicketDTO;
import pizza.api.exceptions.ValidationException;
import pizza.api.mapper.ObjectMapperFactory;
import pizza.api.validators.IValidator;
import pizza.api.validators.TicketValidatorSingleton;
import pizza.service.TicketServiceSingleton;
import pizza.service.api.ITicketService;

@WebServlet(name = "ticketServlet", urlPatterns = "/ticket")
public class TicketServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final ITicketService ticketService = TicketServiceSingleton.getInstance();
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private static final String PARAMETER_ID = "id";
	private final IValidator<TicketDTO> ticketValidator = TicketValidatorSingleton.getInstance();
	private final ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType(CONTENT_TYPE);
		resp.setCharacterEncoding(ENCODING);
		String id = req.getParameter(PARAMETER_ID);
		try {

			if (id != null) {
				if (Long.valueOf(id) > 0) {
					resp.getWriter().write(mapper.writeValueAsString(ticketService.read(Long.valueOf(id))));
					resp.setStatus(HttpServletResponse.SC_OK);
				} else {
					resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
				}
			} else {
				resp.getWriter().write(mapper.registerModule(new JavaTimeModule()).writeValueAsString(ticketService.get()));
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
			TicketDTO ticketDto = mapper.readValue(jsonString, TicketDTO.class);
			try {
				ticketValidator.validate(ticketDto);
			} catch (ValidationException e) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
			ITicket ticket = ticketService.create(ticketDto);
			resp.getWriter().write(mapper.writeValueAsString(ticket));
			resp.setStatus(HttpServletResponse.SC_CREATED);

		} catch (Exception e) {
			resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			System.err.println(e);
		}
	}
	

}
