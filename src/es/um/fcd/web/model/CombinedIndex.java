package es.um.fcd.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CombinedIndex extends Index {
	private int n;
	private double orderIndex;
	private double absenceIndex;

	public CombinedIndex(int n, double orderIndex, double absenceIndex) {
		super();
		this.n = n;
		this.orderIndex = orderIndex;
		this.absenceIndex = absenceIndex;
		this.value = orderIndex * 0.5 + absenceIndex * 0.5;
	}
	
	public double getOrderIndex() {
		return orderIndex;
	}
	
	public double getAbsenceIndex() {		
		return absenceIndex;
	}
	
	public String getExplanation() {
		BigDecimal orderIndexRound = new BigDecimal(orderIndex).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal absenceIndexRound = new BigDecimal(absenceIndex).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal valueRound = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		String explanation = "<h3>Calculation of Combined Index</h3>";
		explanation += "\n1. Top treated (n): " + n;
		explanation += "\n2. Calculate Combined Index by using Order and Absence indexes:";
		explanation += "\nOrder Index x 0.5 + Absence Index x 0.5 = " + orderIndexRound + " x 0.5 + " + absenceIndexRound + " x 0.5 = " + valueRound;
				
		return explanation;
	}
}
