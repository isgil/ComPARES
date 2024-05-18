package es.um.fcd.web.model;

import java.util.LinkedList;
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
	
	public double getMeanPares() {
		double mean = 0;
		for (ParResult result : parResults) {
			mean += result.getMean();
		}
		
		return mean / parResults.size();
	}
	
	public double getMeanTops() {
		double mean = 0;
		for (TopResult result : topResults) {
			mean += result.getMean();
		}
		
		return mean / topResults.size();
	}
	
	public List<Double> getAllParResultsMeans() {
		List<Double> allParResultsMeans = new LinkedList<Double>();
		for (TopResult topResult : topResults) {
			allParResultsMeans.add(topResult.getMean());
		}
		
		return allParResultsMeans;
	}
}
