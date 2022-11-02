package pizza.controllers;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pizza.service.MenuService;
import pizza.service.PizzaInfoServiceSingleton;
import pizza.service.api.IMenuService;
import pizza.service.api.IPizzaInfoService;

import java.io.IOException;

//CRUD controller
//IMenu
@WebServlet(name = "MenuServlet", urlPatterns = "/menu")
public class MenuServlet extends HttpServlet {
	
	private final IMenuService menuService = MenuService.getInstance();
	private static final long serialVersionUID = 1L;
	private final IPizzaInfoService pizzaInfoService = PizzaInfoServiceSingleton.getInstance();
	private static final String CONTENT_TYPE = "application/json";
	private static final String ENCODING = "UTF-8";
	private static final String PARAMETER_ID = "id";  

    //Read POSITION
    //1) Read list
    //2) Read item (card) need id param
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    //CREATE POSITION
    //body json
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
    }

    //UPDATE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    //body json
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    //DELETE POSITION
    //need param id
    //need param version/date_update - optimistic lock
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
