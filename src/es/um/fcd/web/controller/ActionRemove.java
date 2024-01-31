package es.um.fcd.web.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadeTests;
import es.um.fcd.dao.DAOException;
import es.um.fcd.web.model.Notifications;

public class ActionRemove extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response, ServletContext application) {
		System.out.println("Removing tests");
		Notifications notifications = getNotificationsSession(request.getSession());
		String[] testIds = request.getParameterValues("id");
		if (testIds == null || testIds.length == 0) {
			notifications.getError().add(Notifications.ERROR_NO_TEST_SELECTED_TO_REMOVE);
		} else {
			Integer[] ids = new Integer[testIds.length];
			for (int t=0; t<testIds.length; t++) {
				ids[t] = Integer.parseInt(testIds[t]);
			}
			FacadeTests ft = FacadeTests.getInstancia();
			try {
				List<Integer> testsNotRemoved = ft.delete(ids);
				if (testsNotRemoved != null && !testsNotRemoved.isEmpty()) {
					notifications.getError().add(Notifications.getErrorRemovingTests(testsNotRemoved));
				}
			} catch (DAOException e) {
				notifications.getError().add(Notifications.ERROR_REMOVING_TESTS);
				e.printStackTrace();
			}
		}
		
		return new ActionLibrary().execute(request, response, application);
	}
}
