package es.um.fcd.web.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadeSettings;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Settings;
import es.um.fcd.web.model.Notifications;

public class ActionConfig extends Action {
	
	public String execute(HttpServletRequest request,HttpServletResponse response, ServletContext application) {
		FacadeSettings fs = FacadeSettings.getInstancia();
		if (request.getMethod().equalsIgnoreCase("POST")) {
			boolean creatingSettings = false;
			Settings settings = null;
			try {
				settings = fs.getAll();
				if (settings == null) {
					settings = new Settings();
					creatingSettings = true;
				}
			} catch (DAOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String[] selectedTops = request.getParameterValues("top");
			List<Integer> tops = new LinkedList<Integer>();
	        if (selectedTops != null) {
	            for (String top : selectedTops) {
	                tops.add(Integer.valueOf(top));
	            }
	            settings.getSettings().put("tops", tops);
	        } else {
	        	Notifications notifications = getNotificationsSession(request.getSession());
	        	notifications.getWarning().add(Notifications.WARNING_NO_TOPS_SELECTED);
	        	settings.getSettings().remove("tops");
	        }
	        try {
            	if (creatingSettings) {
            		fs.add(settings);
            	} else {
            		fs.update(settings);
            	}
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new ActionLibrary().execute(request, response, application);
		} else {
			try {
				request.setAttribute("tops", fs.getTops());
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return "/WEB-INF/views/config.jsp";
	}
	
	
}