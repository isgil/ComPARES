package es.um.fcd.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OrderIndex extends Index {
	// Top tratado
	private int n;
	// Número de elementos comunes entre listas para el top tratado
	private int k;
	// Distancias entre cada par de títulos comunes a ambas listas
	private List<Integer> distances;
	// Peor orden posible
	private double dOrderMax;
	// Suma de las distancias
	private int dOrder;

	public OrderIndex(int k, int n, List<Integer> distances) {
		super();
		this.k = k;
		this.n = n;
		this.distances = distances;
		this.dOrderMax = (double) k * (n-1);
		this.dOrder = 0;
		for (int distance : distances) {
			this.dOrder += distance;
		}
		if (dOrderMax == 0) {
			this.value = 1;
		} else {
			this.value = (double) dOrder / dOrderMax;
		};
	}
	
	public int getK() {
		return k;
	}
	
	public int getN() {
		return n;
	}
	
	public double getOrderMax() {
		return dOrderMax;
	}
	
	public int getOrder() {		
		return dOrder;
	}
	
	public String getExplanation() {
		BigDecimal dOrderMaxRound = new BigDecimal(dOrderMax).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal valueRound = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		String explanation = "<h5>Calculation of <b>Order Index</b></h5>";
		explanation += "<div class=\"divider\"></div>";
		explanation += "<br/>Top treated: <b>n = " + n + "</b>";
		explanation += "<br/><br/>1. Identify all common elements between the lists for the top treated (k) = " + k;
		explanation += "<br/><br/>2. Calculate the absolute position differences for the common elements:";
		explanation += "<br/> - Distances between common elements, in order: ";
		int numDistances = distances.size();
		int i=0;
		for (int distance : distances) {
			explanation += distance;
			i++;
			if (i < numDistances) explanation += " + ";
		}
		explanation += "<br/> - <b>Order = Sum of differences</b> = <u><span class=\"yellow lighten-4\">" + dOrder + "</span></u>";
		explanation += "<br/><br/>3. Calculate the worst order possible:";
		explanation += "<br/><b>Order(max) = k x (n-1)</b> = " + k + " x (" + n + " - 1) = <u><span class=\"yellow lighten-4\">" + dOrderMaxRound + "</span></u>";
		explanation += "<br/><br/>4. Calculate the Order Index by standardizing order: ";
		explanation += "<br/><b>Order Index = Order / Order(max)</b> = " + dOrder + " / " + dOrderMaxRound + " = " + "<u><span class=\"yellow lighten-4\">" + valueRound + "</span></b>";
		
		
		return explanation;
	}
}
