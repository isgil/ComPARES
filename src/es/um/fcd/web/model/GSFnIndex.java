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
		String explanation = "<h1>Calculation of <b>GSF-n Index</b></h1>";
		explanation += "<div class=\"divider\"></div>";
		
		// 0
		explanation += "<br/><h2>Top calculated:</h2>";
		explanation += "\\[n = " + n + "\\]";
		
		// 1
		explanation += "<br/><h2>1. Calculate the absolute position differences for elements:</h2>";
		explanation += "<i>Elements that do not appear in a list are given the rank <b>n+1</b>, in this case, " + n + " + 1 = " + (n + 1) + "</i>";
		explanation += "<br/>Distances between elements, in order: ";
		
		int numDistances = distances.size();
		int i=0;
		for (int distance : distances) {
			explanation += distance;
			i++;
			if (i < numDistances) explanation += " + ";
		}
		
		// 2
		explanation += "<br/><br/><br/><h2>2. Calculation of GSF.</h2>";
		explanation += "To calculate GSF, all the distances calculated in the previous step are aggregated:";
		explanation += "<br/>\\[GSF = " + GSF + "\\]";
		
		// 3
		explanation += "<br/><br/><h2>3. Calculate the worst GSF possible.</h2>";
		explanation += "\\[k = n\\]";
		explanation += "\\[ GSF_{max} = k \\cdot (k + 1) = " + k + " \\cdot (" + k + " + 1) = " + maxGSF + "\\]";
		
		// 4
		explanation += "<br/><br/><h2>4. Calculation of normalized GSF (GSF-n):</h2>";
		explanation += "\\[ GSF_n = \\frac{GSF}{GSF_{max}} = \\frac{" + GSF + "}{" + maxGSF + "} = " + valueRound.toPlainString() + "\\]";
				
		return explanation;
	}
}
