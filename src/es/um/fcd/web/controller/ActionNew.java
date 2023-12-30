package es.um.fcd.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadeSources;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Source;

public class ActionNew extends Action {
	
	public String execute(HttpServletRequest request, HttpServletResponse response, ServletContext application) {
		if (request.getMethod().equalsIgnoreCase("POST")) {
			FacadeSources fcSources = FacadeSources.getInstancia();
			
			String sourceName = request.getParameter("source");
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
			return "";
		} else {
			return "/WEB-INF/views/new.jsp";
		}
	}
	
	
	
}