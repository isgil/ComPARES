package es.um.fcd.web.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.um.fcd.dao.DAOFactoria;
import es.um.fcd.util.AppLogger;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String HOME = "index.jsp";
	//private static final String LOGIN = "/WEB-INF/views/login/login.jsp";

	// Redefinición método init
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		// Obtiene una factoria DAO y la almacena en la aplicación
		try {
			/*
			DAOFactoria daoFactoria = DAOFactoria.getDAOFactoria(DAOFactoria.MYSQL);
			ServletContext aplicacion = config.getServletContext();
			aplicacion.setAttribute("DAOFactoria", daoFactoria);
			*/
		} catch (Exception e) {
			AppLogger.logException(e);
		}
	}

	public void doGet(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
		procesa(peticion, respuesta);
	}

	public void doPost(HttpServletRequest peticion, HttpServletResponse respuesta) throws ServletException, IOException {
		procesa(peticion, respuesta);
	}

	// Método de procesamiento
	protected void procesa(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		response.setContentType("text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
				
		ServletContext application = getServletConfig().getServletContext();

		// Ruta de la aplicación web
		String dirAplicacion = application.getRealPath("/");

		// Utiliza una clase Helper para analizar la acción a realizar
		RequestHelper requestHelper = new RequestHelper(request, dirAplicacion);

		HttpSession sesion = request.getSession(true);
		
		/*
		// TODO Check language
		if (sesion.getAttribute("idioma") == null){
			Locale locale = peticion.getLocale();
			String idioma = locale.getLanguage();
			if (idioma.equals("es") || idioma.equals("en") || idioma.equals("pt")){
				sesion.setAttribute("idioma", locale.getLanguage());
			} else {
				sesion.setAttribute("idioma", "en");
			}
		}
		*/

		// TODO Login information
		/*
		 * Login login = (Login) sesion.getAttribute("login");
		 */

		// Obtiene la acción a ejecutar asociada a la petición
		Action action = requestHelper.getAccion();

		// TODO Comprobar acceso
		// Si accede a un recurso para el que necesita estar autenticado, se
		// envía a la página de login
		/*
		int nivelAcceso = requestHelper.requiresValidation();

		// Si es necesario estar logeado...
		if (nivelAcceso > 0) {
			if (login == null) {
				// ...y no lo está: Enviar al login
				visualizar(LOGIN, peticion, respuesta);
				return;
			} else {
				// ...si lo está...
				Usuario usuario = login.getUsuario();
				// Pero no tiene nivel de acceso necesario
				if (nivelAcceso > usuario.getGrupo()) {
					// ...enviar al home
					visualizar(HOME, peticion, respuesta);
					return;
				}
			}
		}
		*/

		// Ejecuta la acción y obtiner la vista
		String vista;
		if (action != null) {
			vista = action.execute(request, response, application);
		} else
			vista = HOME;

		// Visualiza el resultado
		visualize(vista, request, response);
	}

	protected void visualize(String view, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Reenvía la petición a la vista
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
}