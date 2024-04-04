package es.um.fcd.web.model;

import java.util.List;

import es.um.fcd.model.Test;

public class TestResult {
	private Test test;
	private List<ParResult> parResults;
	private List<TopResult> topResults;
	
	public TestResult(Test test, List<ParResult> parResults, List<TopResult> topResults) {
		this.test = test;
		this.parResults = parResults;
		this.topResults = topResults;
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

	public List<TopResult> getTopResults() {
		return topResults;
	}

	public void setTopResults(List<TopResult> topResults) {
		this.topResults = topResults;
	}
}
