package es.um.fcd.web.controller;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadeTests;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.Test;
import es.um.fcd.web.model.TestResults;

public class ActionResults extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response, ServletContext application) {
		String[] testIds = request.getParameterValues("id");
		FacadeTests fcTests = FacadeTests.getInstancia();
		List<Test> tests = new LinkedList<Test>();
		for (String testId : testIds) {
			Test test;
			try {
				test = fcTests.get(Integer.getInteger(testId));
				tests.add(test);
				System.out.println(testId);
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		
		List<TestResults> results = new LinkedList<TestResults>();
		for (Test test : tests) {
			Map<Par, Map<String, Integer>> parResults = new LinkedHashMap<Par, Map<String, Integer>>();
			List<Par> pares = test.getPares();
			for (Par par : pares) {
				Map<String, Integer> topValues = new LinkedHashMap<String, Integer>();
				topValues.put("10", 10);
				topValues.put("50", 50);
				topValues.put("100", 100);
				topValues.put("1000", 1000);
				topValues.put("10000", 10000);
				parResults.put(par, topValues);
			}
			TestResults testResults = new TestResults(test, parResults);
			results.add(testResults);
		}
		
		request.setAttribute("results", results);	
		
		return "/WEB-INF/views/results.jspf";
	}

}