package es.um.fcd.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionLibrary extends Action {
	
	public String execute(HttpServletRequest peticion,HttpServletResponse respuesta, ServletContext aplicacion) {
		return "/WEB-INF/views/library.jsp";
	}
}