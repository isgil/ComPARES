package es.um.fcd.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.web.controller.ActionNewTest;
import es.um.fcd.controller.FacadeSources;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Source;
import es.um.fcd.web.controller.ActionNewSource;

public class CreateSource extends MyHttpServlet {
	private static final long serialVersionUID = 1L;

	public CreateSource() {
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
		/*RequestDispatcher rd = request.getRequestDispatcher(new ActionNewSource().execute(request, response, getServletConfig().getServletContext()));
		rd.forward(request, response);*/
		String sourceName = request.getParameter("source");
		// Creating new source
		if (sourceName != null) {
			FacadeSources fcSources = FacadeSources.getInstancia();
			System.out.println("Source to create: " + sourceName);
			Source source;
			try {
				source = fcSources.get(sourceName);
				if (source == null) {
					System.out.println("Success");
					fcSources.add(sourceName);
					response.setStatus(200);
				} else {
					System.out.println("Fail");
					response.setStatus(409);
				}
			} catch (DAOException e) {
				e.printStackTrace();
				response.setStatus(500);
			}
		}
		PrintWriter output = response.getWriter();
		output.println(sourceName);		
	}
}
