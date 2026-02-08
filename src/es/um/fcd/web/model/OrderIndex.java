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
	private double dOrder;

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
			this.value = dOrder / dOrderMax;
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
	
	public double getOrder() {		
		return dOrder;
	}
	
	public String getExplanation() {
		BigDecimal dOrderRound = new BigDecimal(dOrder).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal dOrderMaxRound = new BigDecimal(dOrderMax).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal valueRound = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		String explanation = "<h3>Calculation of Order Index</h3>";
		explanation += "\n1. Top treated (n): " + n;
		explanation += "\n2. Identify all common elements between the lists for the top treated (k) = " + k;
		explanation += "\n3. Calculate the absolute position differences for the common elements:<br/>";
		explanation += "\n - Distances between common elements in order: ";
		int numDistances = distances.size();
		int i=0;
		for (int distance : distances) {
			explanation += distance;
			i++;
			if (i < numDistances) explanation += " + ";
		}
		explanation += "\n - Sum of differences = " + dOrderRound;
		explanation += "\n4. Calculate the worst order possible:";
		explanation += "\nOrder(max) = k x (n-1) = " + k + " x (" + n + " - 1) = " + dOrderMaxRound;
		explanation += "\n5. Calculate the Order Index by standardizing order: ";
		explanation += "\nOrder / Order(max) = " + dOrderRound + " / " + dOrderMaxRound + " = " + valueRound;
		
		
		return explanation;
	}
}
