package es.um.fcd.web.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import es.um.fcd.web.model.Notifications;

public class MyHttpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Notifications getSesionNotifications(HttpSession sesion) {
		Notifications notifications = (Notifications) sesion.getAttribute("notificaciones");
		if (notifications == null) {
			notifications = new Notifications();
			sesion.setAttribute("notificaciones", notifications);
		}

		return notifications;
	}
}
