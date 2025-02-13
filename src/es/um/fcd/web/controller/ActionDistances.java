package es.um.fcd.web.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadePares;
import es.um.fcd.controller.FacadeTests;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.Test;
import es.um.fcd.web.model.Notifications;

public class ActionDistances extends Action {
	
	public String execute(HttpServletRequest request,HttpServletResponse response, ServletContext application) {
		Notifications notifications = getNotificationsSession(request.getSession());
		FacadePares fcPares = FacadePares.getInstancia();
		try {
			String id = request.getParameter("id");
			String[] campos = id.split("-"); 
			int parId = Integer.valueOf(campos[0]);
			Par par = fcPares.get(parId);
			System.out.println(par.getNumTitlesSource1());
			System.out.println(par.getNumTitlesSource2());
			request.setAttribute("par", par);
			request.setAttribute("source1", campos[1]);
			request.setAttribute("source2", campos[2]);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/WEB-INF/views/parts/distances.jspf";	
	}
}