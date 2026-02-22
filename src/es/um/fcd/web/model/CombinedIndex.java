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
		String explanation = "<h5>Calculation of <b>Combined Index</b></h5>";
		explanation += "<div class=\"divider\"></div>";
		explanation += "<br/>Top treated: <b>n = " + n + "</b>";
		explanation += "<br/><br/>Calculate Combined Index by using Order and Absence indexes:";
		explanation += "<br/><b>Combined Index = Order Index x 0.5 + Absence Index x 0.5</b> = <u><span class=\"yellow lighten-4\">" + orderIndexRound + "</span></u> x 0.5 + <u><span class=\"yellow lighten-4\">" + absenceIndexRound + "</u> x 0.5 = " + "<u><span class=\"yellow lighten-4\">" + valueRound + "</span></b>";
				
		return explanation;
	}
}
