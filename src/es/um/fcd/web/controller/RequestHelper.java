package es.um.fcd.web.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import es.um.fcd.model.User;
import es.um.fcd.util.AppLogger;

public class RequestHelper {
	private static final String ACTION_PROPERTIES = "/WEB-INF/properties/accion.properties";
	private static final String ACCESS_PROPERTIES = "/WEB-INF/properties/acceso.properties";

	protected HttpServletRequest peticion;
	protected String dirAplicacion;
	protected String actionKey;

	public RequestHelper(HttpServletRequest peticion, String dirAplicacion) {
		this.peticion = peticion;
		this.dirAplicacion = dirAplicacion;
	}

	public String getActionKey() {
		return this.actionKey;
	}

	public Action getAccion() {
		// Analiza la URI para determinar la acción a realizar
		String uri = peticion.getRequestURI();
		int posIni = uri.lastIndexOf("/");
		int posFin = uri.lastIndexOf(".");
		this.actionKey = uri.substring(posIni + 1, posFin);

		// Recupera el nombre de la clase que representa la acción
		// del fichero de propiedades
		Action action = null;

		// AppLogger.log("RequestHelper actionKey= " + actionKey);

		try {
			InputStream is = new FileInputStream(dirAplicacion + ACTION_PROPERTIES);
			Properties props = new Properties();
			props.load(is);
			String strClaseAccion = props.getProperty(actionKey.toLowerCase());
			// AppLogger.log("RequestHelper strClaseAccion= " + strClaseAccion);

			if (strClaseAccion == null)
				strClaseAccion = "es.um.fcd.web.controller.ActionLibrary";

			// Instacia el objeto Action utilizando reflexión
			Class<?> claseAccion = Class.forName(strClaseAccion);
			action = (Action) claseAccion.newInstance();
		} catch (Exception e) {
			AppLogger.logException(e);
			action = null;
		}

		return action;
	}

	public int requiresValidation() {
		try {
			InputStream is = new FileInputStream(dirAplicacion + ACCESS_PROPERTIES);
			Properties props = new Properties();
			props.load(is);

			String strAcceso = props.getProperty(actionKey.toLowerCase());

			if (strAcceso == null)
				return User.ADMIN;
			return Integer.parseInt(strAcceso);

		} catch (Exception e) {
			AppLogger.logException(e);
		}

		return User.ADMIN;
	}
 
	public boolean navigationError() {
		// Se podría almacenar en un fichero de propiedades las
		// relaciones de navegación entre los recursos y comprobarlas en
		// este método. Se omite su implementación
		return false;
	}
}