package es.um.fcd.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import es.um.fcd.model.Title;

public class AbsenceIndex extends Index {
	// Número de elementos en la lista
	private int n;
	
	// Títulos que están en una lista pero no en la otra, y viceversa.
	private int m;
	
	// Penalización máxima por ausencia final
	private double dAbsenceMax;
	
	// Penalización por ausencia en lista A
	private double dAbsenceA;
	
	// Penalización por ausencia en lista B
	private double dAbsenceB;
	
	// Títulos presentes sólo en source 1
	private List<Title> titlesOnlyInSource1;
	
	// Títulos presentes sólo en source 2
	private List<Title> titlesOnlyInSource2;
	
	// Penalización por ausencia final
	private double dAbsence;

	public AbsenceIndex(int n, List<Title> titlesOnlyInSource1, List<Title> titlesOnlyInSource2, List<Title> titlesInSource1, List<Title> titlesInSource2) {
		super();
		this.n = n;
		this.m = titlesOnlyInSource1.size() + titlesOnlyInSource2.size();
		this.titlesOnlyInSource1 = titlesOnlyInSource1;
		this.titlesOnlyInSource2 = titlesOnlyInSource2;
		this.dAbsence = generateAbsence(titlesOnlyInSource1, titlesOnlyInSource2);
		this.dAbsenceMax = generateAbsenceMax(titlesInSource1, titlesInSource2);
		this.value = dAbsence / dAbsenceMax;
	}
	
	public double generateAbsence(List<Title> titlesOnlyInSource1, List<Title> titlesOnlyInSource2) {
		double dAbsenceA = 0.0;
		double dAbsenceB = 0.0;
	    for (Title title : titlesOnlyInSource1) {
	    	double contribution = 1.0 / Math.log(1 + title.getPositionSource1()); 
	    	System.out.println("Contribution " + title.getTitle() + " = " + contribution);
	        dAbsenceA += contribution;
	    }
	    this.dAbsenceA = dAbsenceA;
	    
	    for (Title title : titlesOnlyInSource2) {
	    	double contribution = 1.0 / Math.log(1 + title.getPositionSource2());
	    	dAbsenceB += contribution;
	    }
	    this.dAbsenceB = dAbsenceB;
	    
	    return (dAbsenceA + dAbsenceB) / 2;
	}
	
	public double generateAbsenceMax(List<Title> titlesInSource1, List<Title> titlesInSource2) {
		double dAbsenceMax = 0.0;
	    for (Title title : titlesInSource1) {
	        dAbsenceMax += 1.0 / Math.log(1 + title.getPositionSource1());
	    }
	    
	    /*
	    for (Title title : titlesInSource2) {
	        dAbsenceMaxB += 1.0 / Math.log(1 + title.getPositionSource2());
	    }
	    this.dAbsenceMaxB = dAbsenceMaxB;
	    */
	    
	    return dAbsenceMax;
	}

	public double getAbsenceMax() {
		return dAbsenceMax;
	}
	
	public double getAbsence() {		
		return dAbsence;
	}
	
	public String getExplanation() {
		BigDecimal dAbsenceRound = new BigDecimal(dAbsence).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal dAbsenceARound = new BigDecimal(dAbsenceA).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal dAbsenceBRound = new BigDecimal(dAbsenceB).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal dAbsenceMaxRound = new BigDecimal(dAbsenceMax).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal valueRound = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		String explanation = "<h1>Calculation of <b>Absence Index</b></h1>";
		explanation += "<div class=\"divider\"></div>";
		
		// 0
		explanation += "<br/><h2>Top calculated:</h2>";
		explanation += "\\[n = " + n + "\\]";
		
		// 1
		explanation += "<br/><h2>1. Identify all the titles present in one list but not in the other, and viveversa:</h2>";
		explanation += "\\[m = " + m + "\\]";
		explanation += "Titles only in list A: <br/>";
		int i = 0;
		for (Title title : titlesOnlyInSource1) {
			i++;
			explanation += " - " + title.getTitle() + "<br/>";
			if (i>10) { 
				explanation += "... <br/>";
				break;
			}
		}
		explanation += "Titles only in list B: <br/>";
		i = 0;
		for (Title title : titlesOnlyInSource2) {
			i++;
			explanation += " - " + title.getTitle() + "<br/>";
			if (i>10) { 
				explanation += "... <br/>";
				break;
			}
		}
		
		// 2
		explanation += "<br/><br/><h2>2. Calculate the absence for the elements.</h2>";
		explanation += "To calculate the Absence metric, we first identify all elements present in list A that are missing from list B. We then repeat the process in reverse, using list B as the reference. ";
		explanation += "In both cases, a penalty is applied based on the position of each element and, to conclude, the result is normalized.";
		explanation += "\\[ D_{ausencia}(A, B) = \\sum_{e \\in A \\setminus B} \\frac{1}{\\log(1 + pos_A(e))} = " + dAbsenceARound.toPlainString() + "\\]";
		explanation += "<br/>\\[ D_{ausencia}(B, A) = \\sum_{e \\in B \\setminus A} \\frac{1}{\\log(1 + pos_B(e))} = " + dAbsenceBRound.toPlainString() + "\\]";
		explanation += "<br/>\\[ D_{ausencia}^{sym} = \\frac{1}{2} (D_{ausencia}(A, B) + D_{ausencia}(B, A)) = \\frac{" + dAbsenceARound.toPlainString() + " + " + dAbsenceBRound.toPlainString() + "}{2} = " + dAbsenceRound.toPlainString() + "\\]";
		
		// 3
		explanation += "<br/><br/><h2>3. Calculate the maximum possible absence.</h2>";
		explanation += "To calculate the maximum possible absence (extreme case, zero overlap), it is considered that none of the elements of one list is present in the other one.";
		explanation += "For this calculation, the result is the same taking A or B as reference, therefore we only use (A,B)";
		explanation += "\\[ D_{ausencia}^{max}(A, B) = \\sum_{e \\in A} \\frac{1}{\\log(1 + pos_A(e))} = " + dAbsenceMaxRound.toPlainString() + "\\]";
		
		// 4
		explanation += "<br/><br/><h2>4. Calculate Absence Index by standardizing absence:</h2>";
		explanation += "\\[ I_{ausencia} = \\frac{D_{ausencia}^{sym}}{D_{ausencia}^{max, sym}} = \\frac{" + dAbsenceRound.toPlainString() + "}{" + dAbsenceMaxRound.toPlainString() + "} = " + valueRound.toPlainString() + "\\]"; 
		
		return explanation;
	}
}
