package es.um.fcd.web.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionConfig extends Action {
	
	public String execute(HttpServletRequest request,HttpServletResponse response, ServletContext application) {
		if (request.getMethod().equalsIgnoreCase("POST")) {
			String[] selectedTops = request.getParameterValues("top");
	        if (selectedTops != null) {
	            for (String top : selectedTops) {
	                System.out.println("Selected top: " + top);
	            }
	        } else {
	            System.out.println("No tops selected.");
	        }
			return new ActionLibrary().execute(request, response, application);
		}
		
		return "/WEB-INF/views/config.jsp";
	}
	
	
}