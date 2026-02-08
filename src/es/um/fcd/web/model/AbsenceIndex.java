package es.um.fcd.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AbsenceIndex extends Index {
	private int m;
	private int n;
	private double λn;
	private double dAbsenceMax;
	private double dAbsence;

	public AbsenceIndex(int m, int n) {
		super();
		this.m = m;
		this.n = n;
		this.λn = 3 + 2 * Math.log(n);
		this.dAbsenceMax = (double) (n*2) * λn;
		this.dAbsence = (double) m * λn;
		this.value = dAbsence / dAbsenceMax;
	}
	
	public int getM() {
		return m;
	}
	
	public int getN() {
		return n;
	}
	
	public double getλn() {
		return λn;
	}

	public double getAbsenceMax() {
		return dAbsenceMax;
	}
	
	public double getAbsence() {		
		return dAbsence;
	}
	
	public String getExplanation() {
		BigDecimal λnRound = new BigDecimal(λn).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal dAbsenceRound = new BigDecimal(dAbsence).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal dAbsenceMaxRound = new BigDecimal(dAbsenceMax).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		BigDecimal valueRound = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		String explanation = "<h3>Calculation of Absence Index</h3>";
		explanation += "\n1. Top treated: " + n;
		explanation += "\n2. Identify all the titles present in one list but not in the other, and viveversa (m) = " + m;
		explanation += "\n3. Calculate the penalty for absence:<br/>";
		explanation += "\nλ(n) = 3 + 2 log(n) = 3 + 2 log(" + n + ") = " + λnRound;
		explanation += "\n4. Calculate the absence for the elements:<br/>";
		explanation += "\nAbsence = m x λ(n) = " + m + " x λ(" + n + ") = " + dAbsenceRound;
		explanation += "\n5. Calculate the maximum possible absence:<br/>";
		explanation += "\nAbsence(max) = n x λ(n) = " + n + " x λ(" + n + ") = " + dAbsenceMaxRound;
		explanation += "\n6. Calculate Absence Index by standardizing absence:<br/>";
		explanation += "\nAbsence / Absence(max) = " + dAbsenceRound + " / " + dAbsenceMaxRound + " = " + valueRound; 
		
		return explanation;
	}
}
