package es.um.fcd.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CombinedIndex extends Index {
	private int n;
	private double orderIndex;
	private double absenceIndex;
	private double dynamicPenalty;
	private double baseIndex;

	public CombinedIndex(int n, double orderIndex, double absenceIndex) {
		super();
		this.n = n;
		this.orderIndex = orderIndex;
		this.absenceIndex = absenceIndex;
		this.dynamicPenalty = Math.max(0.1, 1 - (0.5 / Math.pow(Math.log(1+n), 0.5)));
		this.baseIndex = orderIndex * 0.5 + absenceIndex * 0.5;
		this.value = baseIndex * dynamicPenalty;
		
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
		BigDecimal baseIndexRound = new BigDecimal(baseIndex).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal dynamicPenaltyRound = new BigDecimal(dynamicPenalty).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal valueRound = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		String explanation = "<h1>Calculation of <b>Combined Index</b></h1>";
		explanation += "<div class=\"divider\"></div>";
		
		// 0
		explanation += "<br/><h2>Top calculated:</h2>";
		explanation += "\\[n = " + n + "\\]";
		
		// 1 
		explanation += "<br/><h2>1. Calculate Base Index by using Order and Absence indexes:</h2>";
		explanation += "\\[ I_{base} = \\frac{I_{orden} + I_{ausencia}}{2} = \\frac{" + orderIndexRound.toPlainString() + " + " + absenceIndexRound.toPlainString() + "}{2} = " + baseIndexRound.toPlainString() + "\\]";
		
		// 2
		explanation += "<br/><h2>2. Calculate the dynamic penalty applied.</h2>";
		explanation += "A dynamic penalty based on list size is introduced to model the statistical confidence of the result as a function of its scale, without relying on a predefined maximum value.";
		explanation += "\\[ \\lambda(n) = \\max \\left( 0.1, 1 - \\frac{0.5}{\\log(1 + n)^{0.5}} \\right) = " + dynamicPenaltyRound.toPlainString() + "\\]";
		
		// 3
		explanation += "<br/><h2>3. Calculate Combined Index.</h2>";
		explanation += "\\[ I_{combined}(A, B) = \\lambda(n) \\cdot I_{base}(A, B) = " + dynamicPenaltyRound.toPlainString() + " \\cdot" + baseIndexRound.toPlainString() + " = " + valueRound.toPlainString() + "\\]";				
				
		return explanation;
	}
}
