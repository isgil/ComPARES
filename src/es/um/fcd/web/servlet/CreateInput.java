package es.um.fcd.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.web.controller.ActionNewTest;

public class CreateInput extends MyHttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateInput() {
		super();
	}

	protected void forwardIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(new ActionNewTest().execute(request, response, getServletConfig().getServletContext()));
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String inputId = request.getParameter("inputFileId");
		System.out.println("ID input=" + inputId);
		request.setAttribute("inputFileId", inputId);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/parts/input-file.jspf");
		dispatcher.forward(request, response);
	}
}
