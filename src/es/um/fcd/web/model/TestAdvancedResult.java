package es.um.fcd.web.model;

import java.util.LinkedList;
import java.util.List;

import es.um.fcd.model.Test;

public class TestAdvancedResult {
	private Test test;
	private List<ParTopResult> parTopResults;
	private List<TopAdvancedResult> topAdvancedResults;
	
	public TestAdvancedResult(Test test, List<ParTopResult> parTopResults, List<TopAdvancedResult> topAdvancedResults) {
		this.test = test;
		this.parTopResults = parTopResults;
		this.topAdvancedResults = topAdvancedResults;		
	}
	
	public Test getTest() {
		return test;
	}
	
	public void setTest(Test test) {
		this.test = test;
	}
	
	public List<ParTopResult> getParResults() {
		return parTopResults;
	}
	
	public void setParResults(List<ParTopResult> parTopResults) {
		this.parTopResults = parTopResults;
	}

	public List<TopAdvancedResult> getTopResults() {
		return topAdvancedResults;
	}

	public void setTopResults(List<TopAdvancedResult> topAdvancedResults) {
		this.topAdvancedResults = topAdvancedResults;
	}
	
	public double getMeanPares() {
		double mean = 0;
		for (ParTopResult result : parTopResults) {
			mean += result.getMean();
		}
		
		return mean / parTopResults.size();
	}
	
	public double getMeanTops() {
		double mean = 0;
		for (TopAdvancedResult result : topAdvancedResults) {
			mean += result.getMean();
		}
		
		return mean / topAdvancedResults.size();
	}
	
	public List<Double> getAllParResultsMeans() {
		List<Double> allParResultsMeans = new LinkedList<Double>();
		for (TopAdvancedResult topAdvancedResult : topAdvancedResults) {
			allParResultsMeans.add(topAdvancedResult.getMean());
		}
		
		return allParResultsMeans;
	}
}
