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
		String explanation = "<h5>Calculation of <b>Absence Index</b></h5>";
		explanation += "<div class=\"divider\"></div>";
		explanation += "<br/>Top treated: <b>n = " + n + "</b>";
		explanation += "<br/><br/>1. Identify all the titles present in one list but not in the other, and viveversa: <b>m = " + m + "</b>";
		explanation += "<br/><br/>2. Calculate the penalty for absence:";
		explanation += "<br/><b>λ(n) = 3 + 2 log(n)</b> = 3 + 2 log(" + n + ") = " + λnRound;
		explanation += "<br/><br/>3. Calculate the absence for the elements:";
		explanation += "<br/><b>Absence = m x λ(n)</b> = " + m + " x λ(" + n + ") = <u><span class=\"yellow lighten-4\">" + dAbsenceRound + "</span></u>";
		explanation += "<br/><br/>4. Calculate the maximum possible absence:";
		explanation += "<br/><b>Absence(max) = n x λ(n)</b> = " + n + " x λ(" + n + ") = <u><span class=\"yellow lighten-4\">" + dAbsenceMaxRound + "</span></u>";
		explanation += "<br/><br/>5. Calculate Absence Index by standardizing absence:";
		explanation += "<br/><b>Absence Index = Absence / Absence(max)</b> = " + dAbsenceRound + " / " + dAbsenceMaxRound + " = " + "<u>" + valueRound + "</span></u>"; 
		
		return explanation;
	}
}
