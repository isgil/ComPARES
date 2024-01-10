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
				test = fcTests.get(Integer.parseInt(testId));
				tests.add(test);
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
				if (par.getId() == 1) {
					topValues.put("10", 99);
					topValues.put("50", 99);
					topValues.put("100", 99);
					topValues.put("200", 99);
					topValues.put("300", 99);
					topValues.put("400", 99);
					topValues.put("500", 99);
					topValues.put("750", 99);
					topValues.put("1K", 99);
					topValues.put("1.5K", 99);
					topValues.put("2K", 99);
//					topValues.put("2.5K", 99);
//					topValues.put("3K", 99);
//					topValues.put("3.5K", 99);
//					topValues.put("4K", 99);
//					topValues.put("4.5K", 99);
				} else {
					topValues.put("10", 99);
					topValues.put("50", 99);
					topValues.put("100", 99);
					topValues.put("200", 99);
					topValues.put("300", 99);
					topValues.put("400", 99);
					topValues.put("500", 99);
					topValues.put("750", 99);
					topValues.put("1K", 99);
					topValues.put("1.5K", 99);
					topValues.put("2K", 99);
					topValues.put("2.5K", 99);
					topValues.put("3K", 99);
					topValues.put("3.5K", 99);
					topValues.put("4K", 99);
					topValues.put("4.5K", 99);
					topValues.put("5K", 99);
//					topValues.put("5.5K", 99);
//					topValues.put("6K", 99);
//					topValues.put("6.5K", 99);
//					topValues.put("7K", 99);
//					topValues.put("7.5K", 99);
//					topValues.put("8K", 99);
//					topValues.put("8.5K", 99);
//					topValues.put("9K", 99);
//					topValues.put("9.5K", 99);
//					topValues.put("100K", 99);
				}
				
				
//				topValues.put("10", 99);
//				topValues.put("50", 99);
//				topValues.put("100", 99);
//				topValues.put("200", 99);
//				topValues.put("300", 99);
//				topValues.put("400", 99);
//				topValues.put("500", 99);
//				topValues.put("750", 99);
//				topValues.put("1000", 99);
//				topValues.put("1500", 99);
//				topValues.put("2000", 99);
//				topValues.put("2500", 99);
//				topValues.put("3000", 99);
//				topValues.put("3500", 99);
//				topValues.put("4000", 99);
//				topValues.put("4500", 99);
//				topValues.put("5000", 99);
//				topValues.put("5500", 99);
//				topValues.put("6000", 99);
//				topValues.put("6500", 99);
//				topValues.put("7000", 99);
//				topValues.put("7500", 99);
//				topValues.put("8000", 99);
//				topValues.put("8500", 99);
//				topValues.put("9000", 99);
//				topValues.put("9500", 99);
//				topValues.put("10000", 99);
				parResults.put(par, topValues);
			}
			TestResults testResults = new TestResults(test, parResults);
			results.add(testResults);
		}
		/*
		for (TestResults testResults : results) {
			System.out.println("Test evaluated: " + testResults.getTest().getId());
			for (Par par : testResults.getTestResults().keySet()) {
				System.out.println("Par evaluated: " + par.getId());
				for (String top : testResults.getTestResults().get(par).keySet()) {
					System.out.println("#Top: " + top + " / " + testResults.getTestResults().get(par).get(top));
				}
			}
		}
		*/
		
		request.setAttribute("results", results);	
		
		return "/WEB-INF/views/parts/results.jspf";
	}
}
