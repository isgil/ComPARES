package es.um.fcd.web.model;

import java.util.Map;

import es.um.fcd.model.Par;

public class ParResult {
	private Par par;
	private Map<Integer, Integer> topResults;
	
	public ParResult(Par par, Map<Integer, Integer> topResults) {
		this.par = par;
		this.topResults = topResults;
	}
	
	public Par getPar() {
		return par;
	}
	
	public void setPar(Par par) {
		this.par = par;
	}
	
	public Map<Integer, Integer> getTopResults() {
		return topResults;
	}
	
	public void setTopResults(Map<Integer, Integer> topResults) {
		this.topResults = topResults;
	}
}