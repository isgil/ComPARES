package es.um.fcd.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.um.fcd.model.User;
//import es.um.tfg.atfc.web.modelo.Login;
import es.um.fcd.web.model.Notifications;

public abstract class Action {
	public abstract String execute(HttpServletRequest request, HttpServletResponse response, ServletContext application);
	
	public User getCurrentUser(HttpSession session) {
		/*
		Login login = (Login) session.getAttribute("login");
		if (login != null) {
			return login.getUsuario();
		}
		*/

		return null;
	}

	public Notifications getNotificacionesSesion(HttpSession sesion) {
		Notifications notifications = (Notifications) sesion.getAttribute("notificaciones");
		if (notifications == null) {
			notifications = new Notifications();
			sesion.setAttribute("notificaciones", notifications);
		}

		return notifications;
	}
}