package es.um.fcd.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.web.controller.ActionLibrary;

public class UpdateProgress extends MyHttpServlet {
	private static final long serialVersionUID = 1L;

	public UpdateProgress() {
		super();
	}

	protected void reenviarIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(new ActionLibrary().execute(request, response, getServletConfig().getServletContext()));
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer value = (Integer) request.getSession().getAttribute("loadPercentage");
		PrintWriter outPrintWriter = response.getWriter();
		if (value != null) {
			System.out.println("progreso = " + value);
			outPrintWriter.print(value);
		} /*else {
			outPrintWriter.print("");
		}*/
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
