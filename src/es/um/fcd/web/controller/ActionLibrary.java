package es.um.fcd.web.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadeTests;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Test;
import es.um.fcd.web.model.Notifications;

public class ActionLibrary extends Action {
	
	public String execute(HttpServletRequest request,HttpServletResponse response, ServletContext application) {
		FacadeTests fcTests = FacadeTests.getInstancia();
		try {
			List<Test> tests = (List<Test>) fcTests.getAll();
			request.setAttribute("tests", tests);
			Notifications notifications = getNotificationsSession(request.getSession());
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/WEB-INF/views/library.jsp";
	}
}