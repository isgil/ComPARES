package es.um.fcd.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionAbout extends Action {
	
	public String execute(HttpServletRequest request,HttpServletResponse response, ServletContext application) {		
		return "/WEB-INF/views/about.jsp";
	}
}