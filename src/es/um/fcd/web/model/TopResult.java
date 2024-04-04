package es.um.fcd.web.model;

import java.util.LinkedList;
import java.util.List;

public class TopResult {
	private int top;
	private List<AdvancedParResult> advancedParResults;
	
	public TopResult(int top) {
		this.top = top;
		this.advancedParResults = new LinkedList<AdvancedParResult>();
	}
	
	public TopResult(int top, List<AdvancedParResult> parResults, double mean) {
		this.top = top;
		this.advancedParResults = parResults;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public List<AdvancedParResult> getAdvancedParResults() {
		return advancedParResults;
	}

	public void setAdvancedParResults(List<AdvancedParResult> parResults) {
		this.advancedParResults = parResults;
	}

	public double getMean() {
		double accumulatedProximity = 0;
		for (AdvancedParResult advancedParResult : advancedParResults) {
			accumulatedProximity += advancedParResult.getProximity();
		}
		
		return accumulatedProximity / advancedParResults.size();
	}
}