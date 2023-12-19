package es.um.fcd.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Action404 extends Action {
	
	// No se encontró la página indicada
	public String execute(HttpServletRequest peticion,HttpServletResponse respuesta, ServletContext aplicacion) {
		return "404.jsp";
	}
}