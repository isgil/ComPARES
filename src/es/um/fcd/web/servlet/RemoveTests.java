package es.um.fcd.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.web.controller.ActionLibrary;
import es.um.fcd.web.controller.ActionNewTest;
import es.um.fcd.web.model.Notifications;
import es.um.fcd.controller.FacadeSources;
import es.um.fcd.controller.FacadeTests;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Source;

public class RemoveTests extends MyHttpServlet {
	private static final long serialVersionUID = 1L;

	public RemoveTests() {
		super();
	}

	protected void forwardIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(new ActionLibrary().execute(request, response, getServletConfig().getServletContext()));
		rd.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Removing tests");
		Notifications notifications = (Notifications) request.getSession().getAttribute("notifications");
		if (notifications == null) {
			notifications = new Notifications();
			request.getSession().setAttribute("notifications", notifications);
		}
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
				} else {
					notifications.getSuccess().add(Notifications.TESTS_REMOVED_SUCCESSFULLY);
				}
			} catch (DAOException e) {
				notifications.getError().add(Notifications.ERROR_REMOVING_TESTS);
				e.printStackTrace();
			}
		}
		
		forwardIndex(request, response);
	}
}
