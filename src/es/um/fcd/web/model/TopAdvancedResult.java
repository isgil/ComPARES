package es.um.fcd.web.model;

import java.util.LinkedList;
import java.util.List;

public class TopAdvancedResult {
	private int top;
	private List<ParAdvancedResult> parAdvancedResults;
	
	public TopAdvancedResult(int top) {
		this.top = top;
		this.parAdvancedResults = new LinkedList<ParAdvancedResult>();
	}
	
	public TopAdvancedResult(int top, List<ParAdvancedResult> parResults, double mean) {
		this.top = top;
		this.parAdvancedResults = parResults;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public List<ParAdvancedResult> getAdvancedParResults() {
		return parAdvancedResults;
	}

	public void setAdvancedParResults(List<ParAdvancedResult> parResults) {
		this.parAdvancedResults = parResults;
	}

	public double getMean() {
		double accumulatedProximity = 0;
		for (ParAdvancedResult parAdvancedResult : parAdvancedResults) {
			accumulatedProximity += parAdvancedResult.getProximity();
		}
		
		return accumulatedProximity / parAdvancedResults.size();
	}
}