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
	
	public double getOrderIndexMean() {
		int numTops = parResults.size();
		double accumulatedOrderIndex = 0;
		for (ParResult parResult : parResults) {
			accumulatedOrderIndex += parResult.getOrderIndex();
		}
		
		double orderIndexMean = accumulatedOrderIndex / (double) numTops;
		
		return orderIndexMean;
	}
	
	public double getAbsenceIndexMean() {
		int numTops = parResults.size();
		double accumulatedAbsenceIndex = 0;
		for (ParResult parResult : parResults) {
			accumulatedAbsenceIndex += parResult.getAbsenceIndex();
		}
		
		double indexAbsenceMean = accumulatedAbsenceIndex / (double) numTops;
		
		return indexAbsenceMean;
	}
	
	public double getCombinedIndexMean() {
		int numTops = parResults.size();
		double accumulatedCombinedIndex = 0;
		for (ParResult parResult : parResults) {
			accumulatedCombinedIndex += parResult.getCombinedIndex();
		}
		
		double indexCombinedMean = accumulatedCombinedIndex / (double) numTops;
		
		return indexCombinedMean;
	}
}
