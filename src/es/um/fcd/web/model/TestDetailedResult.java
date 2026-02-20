package es.um.fcd.web.model;

import java.util.Map;

public class TestDetailedResult {
	private TestResult testResults;
	private Map<String, TopResultDetailed> topResultsDetailed;
	
	public TestDetailedResult(TestResult testResults, Map<String, TopResultDetailed> topResultsDetailed) {
		this.testResults = testResults;
		this.topResultsDetailed = topResultsDetailed;
	}
	
	public TestResult getTestResults() {
		return testResults;
	}
	
	public void setTestResults(TestResult testResults) {
		this.testResults = testResults;
	}

	public Map<String, TopResultDetailed> getTopResultsDetailed() {
		return topResultsDetailed;
	}

	public void setTopResultsDetailed(Map<String, TopResultDetailed> topResultsDetailed) {
		this.topResultsDetailed = topResultsDetailed;
	}
}
