package es.um.fcd.web.model;

import java.util.Map;

import es.um.fcd.model.Par;

public class ParTopResult {
	private Par par;
	private Map<Integer, TopResult> topResults;
	private double mean;
	
	public ParTopResult(Par par, Map<Integer, TopResult> topResults, double mean) {
		this.par = par;
		this.topResults = topResults;
		this.mean = mean;
	}
	
	public Par getPar() {
		return par;
	}
	
	public void setPar(Par par) {
		this.par = par;
	}
	
	public Map<Integer, TopResult> getTopResults() {
		return topResults;
	}
	
	public void setTopResults(Map<Integer, TopResult> topResults) {
		this.topResults = topResults;
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}
	
	public double getGSFnIndexMean() {
		int numTops = topResults.size();
		double accumulatedGSFnIndex = 0;
		for (int top : topResults.keySet()) {
			accumulatedGSFnIndex += topResults.get(top).getGSFnIndex();
		}
		double indexGSFnMean = accumulatedGSFnIndex / (double) numTops;
		
		return indexGSFnMean;
	}
	
	public double getCombinedIndexMean() {
		int numTops = topResults.size();
		double combinedIndex = 0;
		for (int top : topResults.keySet()) {
			combinedIndex += topResults.get(top).getCombinedIndex();
		}
		double combinedIndexMean = combinedIndex / (double) numTops;
		
		return combinedIndexMean;
	}
	
	public double getOrderIndexMean() {
		int numTops = topResults.size();
		double orderIndex = 0;
		for (int top : topResults.keySet()) {
			orderIndex += topResults.get(top).getOrderIndex();
		}
		double orderIndexMean = orderIndex / (double) numTops;
		
		return orderIndexMean;
	}
	
	public double getAbsenceIndexMean() {
		int numTops = topResults.size();
		double absenceIndex = 0;
		for (int top : topResults.keySet()) {
			absenceIndex += topResults.get(top).getAbsenceIndex();
		}
		double absenceIndexMean = absenceIndex / (double) numTops;
		
		return absenceIndexMean;
	}
}