package es.um.fcd.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadeSources;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Source;
import es.um.fcd.web.controller.ActionNew;

public class CreateSource extends MyHttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateSource() {
		super();
	}

	protected void forwardIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(new ActionNew().execute(request, response, getServletConfig().getServletContext()));
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(new ActionNew().execute(request, response, getServletConfig().getServletContext()));
		rd.forward(request, response);
	}
}
