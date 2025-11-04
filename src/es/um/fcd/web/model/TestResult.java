package es.um.fcd.web.model;

import java.util.List;

import es.um.fcd.model.Test;

public class TestResult {
	private Test test;
	private List<ParTopResult> parTopResults;
	
	public TestResult(Test test, List<ParTopResult> parResults) {
		this.test = test;
		this.parTopResults = parResults;
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

	public void setParResults(List<ParTopResult> parResults) {
		this.parTopResults = parResults;
	}
	
	public double getGSFnIndexMean() {
		int numPares = parTopResults.size();
		double accumulatedGSFnIndex = 0;
		for (ParTopResult parTopResult : parTopResults) {
			accumulatedGSFnIndex += parTopResult.getGSFnIndexMean();
		}
		
		double indexGSFnMean = accumulatedGSFnIndex / (double) numPares;
		
		return indexGSFnMean;
	}
	
	public double getCombinedIndexMean() {
		int numPares = parTopResults.size();
		double accumulatedCombinedIndex = 0;
		for (ParTopResult parTopResult : parTopResults) {
			accumulatedCombinedIndex += parTopResult.getCombinedIndexMean();
		}
		
		double indexCombinedMean = accumulatedCombinedIndex / (double) numPares;
		
		return indexCombinedMean;
	}
	
	public double getOrderIndexMean() {
		int numPares = parTopResults.size();
		double accumulatedOrderIndex = 0;
		for (ParTopResult parTopResult : parTopResults) {
			accumulatedOrderIndex += parTopResult.getOrderIndexMean();
		}
		
		double orderIndexMean = accumulatedOrderIndex / (double) numPares;
		
		return orderIndexMean;
	}
	
	public double getAbsenceIndexMean() {
		int numPares = parTopResults.size();
		double accumulatedAbsenceIndex = 0;
		for (ParTopResult parTopResult : parTopResults) {
			accumulatedAbsenceIndex += parTopResult.getAbsenceIndexMean();
		}
		
		double indexAbsenceMean = accumulatedAbsenceIndex / (double) numPares;
		
		return indexAbsenceMean;
	}
}
