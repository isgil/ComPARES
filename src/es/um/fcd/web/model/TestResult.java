package es.um.fcd.web.model;

import java.util.List;

import es.um.fcd.model.Test;

public class TestResult {
	private Test test;
	private List<ParResult> parResults;
	
	public TestResult(Test test, List<ParResult> parResults) {
		this.test = test;
		this.parResults = parResults;
	}
	
	public Test getTest() {
		return test;
	}
	
	public void setTest(Test test) {
		this.test = test;
	}
	
	public List<ParResult> getParResults() {
		return parResults;
	}
	
	public void setParResults(List<ParResult> parResults) {
		this.parResults = parResults;
	}
}
