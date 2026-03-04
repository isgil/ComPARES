package es.um.fcd.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class GSFnIndex extends Index {
	private int n;
	private int k;
	private int maxRank;
	private int maxGSF;
	private List<Integer> distances;
	private int GSF;

	public GSFnIndex(int n, int k, List<Integer> distances) {
		super();
		this.n = n;
		this.k = k;
		this.maxRank = n+1;
		this.maxGSF = k * (k + 1);
		this.distances = distances;
		this.GSF = 0;
		for (int distance : distances) {
			this.GSF += distance;
		}
		
		this.value = (double) GSF / (double) maxGSF;
	}
	
	public int getN() {
		return n;
	}
	
	public int getK() {
		return k;
	}

	public int getMaxRank() {
		return maxRank;
	}
	
	public int getMaxGSF() {
		return maxGSF;
	}
	
	public List<Integer> getDistances(){
		return distances;
	}
	
	public int getGSF() {
		return GSF;
	}
	
	public String getExplanation() {
		BigDecimal valueRound = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		String explanation = "<h5>Calculation of <b>GSF-n Index</b></h5>";
		explanation += "<div class=\"divider\"></div>";
		explanation += "<br/>Top treated: <b>n = " + n + "</b>";
		explanation += "<br/><br/>1. Elements that do not appear in a list are given the rank <b>n+1</b>, in this case, " + n + " + 1 = " + (n + 1);
		explanation += "<br/><br/>2. Calculate the absolute position differences for elements:";
		explanation += "<br/> - Distances between elements, in order: ";
		int numDistances = distances.size();
		int i=0;
		for (int distance : distances) {
			explanation += distance;
			i++;
			if (i < numDistances) explanation += " + ";
		}
		explanation += "<br/> - <b>GSF = Sum of differences</b> = <u><span class=\"yellow lighten-4\">" + GSF + "</span></u>";
		explanation += "<br/><br/>3. Calculate the worst GSF possible, where <b>k = n</b>:";
		explanation += "<br/><b>GSF(max) = k x (k + 1)</b> = " + k + " x (" + k + " + 1) = <u><span class=\"yellow lighten-4\">" + maxGSF + "</span></u>";
		explanation += "<br/><br/>4. Calculation of normalized GSF (GSF-n):";
		explanation += "<br/><b>GSF-n = GSF / GSF(max)</b> = " + GSF + " / " + maxGSF + " = " + "<u><span class=\"yellow lighten-4\">" + valueRound.toPlainString() + "</span></u>";
				
		return explanation;
	}
}
