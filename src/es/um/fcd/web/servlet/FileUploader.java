package es.um.fcd.web.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.util.AppLogger;
import es.um.fcd.web.controller.ActionLibrary;

public abstract class FileUploader extends MyHttpServlet {
	private static final long serialVersionUID = 1L;
	protected String filesDir;

	public void init(ServletConfig config) {
		try {
			super.init(config);

			// Recuperamos el directorio donde se guardaran los documentos
			filesDir = config.getInitParameter(getDirectory());
		} catch (Exception e) {
			AppLogger.logException(e);
		}
	}
	
	protected abstract String getDirectory();

	protected void fordwardIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(new ActionLibrary().execute(request, response, getServletConfig().getServletContext()));
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		fordwardIndex(request, response);
	}
}
