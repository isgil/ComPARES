package es.um.fcd.web.model;

import java.util.Map;

import es.um.fcd.model.Par;

public class ParResult {
	private Par par;
	private Map<Integer, Double> topResults;
	private double mean;
	
	public ParResult(Par par, Map<Integer, Double> topResults, double mean) {
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
	
	public Map<Integer, Double> getTopResults() {
		return topResults;
	}
	
	public void setTopResults(Map<Integer, Double> topResults) {
		this.topResults = topResults;
	}

	public double getMean() {
		return mean;
	}

	public void setMean(double mean) {
		this.mean = mean;
	}
}