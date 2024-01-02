package es.um.fcd.web.model;

import java.util.Map;

import es.um.fcd.model.Par;
import es.um.fcd.model.Test;

public class TestResults {
	private Test test;
	private Map<Par, Map<String, Integer>> results;
	
	public TestResults(Test test, Map<Par, Map<String, Integer>> results) {
		this.test = test;
		this.results = results;
	}
	
	public Test getTest() {
		return test;
	}
	
	public void setTest(Test test) {
		this.test = test;
	}
	
	public Map<Par, Map<String, Integer>> getResults() {
		return results;
	}
	
	public void setResults(Map<Par, Map<String, Integer>> results) {
		this.results = results;
	}
}
