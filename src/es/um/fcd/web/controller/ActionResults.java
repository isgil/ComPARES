package es.um.fcd.web.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.um.fcd.controller.FacadeTests;
import es.um.fcd.controller.TestController;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Test;
import es.um.fcd.web.model.TestResult;
import es.um.fcd.web.model.ParResult;

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
		
		TestController tc = TestController.getInstancia();
		List<TestResult> testsResults = new LinkedList<TestResult>();
		for (Test test : tests) {
			TestResult result = null;
			try {
				result = tc.getTestResult(test);
			} catch (DAOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			testsResults.add(result);
		}
		
		for (TestResult testResult : testsResults) {
			System.out.println("Test: " + testResult.getTest().getName());
			List<ParResult> parResults = testResult.getParResults();
			for (ParResult parResult : parResults) {
				System.out.println("--Par: " + parResult.getPar().getId());
				Map<Integer, Integer> topResults = parResult.getTopResults();
				for (Integer top : topResults.keySet()) {
					System.out.println("----Top: " + top + " --> " + topResults.get(top));
				}
			}
		}
		
		//request.setAttribute("results", generateDummyResults(tests));
		request.setAttribute("testsResults", testsResults);
		
		return "/WEB-INF/views/parts/results.jspf";
	}
}
