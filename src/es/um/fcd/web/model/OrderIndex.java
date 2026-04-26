package es.um.fcd.web.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import es.um.fcd.model.Title;

public class OrderIndex extends Index {
	// Top tratado
	private int n;
	
	// Número de elementos comunes entre listas para el top tratado
	private int k;
	
	// Elementos comunes entre listas para el top tratado
	private List<Title> commonTitles;
	
	// Peor orden posible tomando A como referencia
	private double dOrderMaxA;
	
	// Peor orden posible tomando B como referencia
	private double dOrderMaxB;
	
	// Peor orden posible final
	private double dOrderMax;
	
	// Suma de las contribuciones tomando la lista A como referencia
	private double dOrderA;
	
	// Suma de las contribuciones tomando la lista B como referencia
	private double dOrderB;
	
	// Contribuciones finales
	private double dOrder;

	public OrderIndex(int n, List<Title> commonTitles) {
		super();
		this.commonTitles = commonTitles;
		this.k = commonTitles.size();
		this.n = n;
		
		this.dOrder = generateOrder(commonTitles);
		this.dOrderMax = generateOrderMax(n, commonTitles);
		
		if (dOrderMax == 0) {
			this.value = 1;
		} else {
			this.value = (double) dOrder / dOrderMax;
		};
	}
	
	private double generateOrder(List<Title> commonTitles) {

		double dOrderA = 0.0;
		double dOrderB = 0.0;

		for (Title title : commonTitles) {
			int posA = title.getPositionSource1();
			int posB = title.getPositionSource2();

			// Término: (1 / log(1 + posA)) * |posA - posB|
			double penalty = 1.0 / Math.log(1 + posA);
			double distance = Math.abs(posA - posB);

			double contribution = penalty * distance;
			System.out.println("Contribution " + title.getTitle() + " = " + contribution);
			dOrderA += contribution;
		}
		
		this.dOrderA = dOrderA;
		System.out.println("****************");
		
		for (Title title : commonTitles) {
			int posA = title.getPositionSource1();
			int posB = title.getPositionSource2();

			// Término: (1 / log(1 + posA)) * |posA - posB|
			double penalty = 1.0 / Math.log(1 + posB);
			double distance = Math.abs(posB - posA);

			double contribution = penalty * distance; 
			System.out.println("Contribution " + title.getTitle() + " = " + contribution);
			dOrderB += contribution;
		}
		
		this.dOrderB = dOrderB;

		return (dOrderA + dOrderB) / 2;
	}

	private double generateOrderMax(int n, List<Title> commonTitles) {
		double dOrderMaxA = 0.0;
		double dOrderMaxB = 0.0;

		for (Title title : commonTitles) {
			// La posición en A es i
			// La peor posición en B sería n - i
			double posA = title.getPositionSource1();
			double posB = n - posA;

			double penalty = 1.0 / Math.log(1 + posA);
			//double distance = Math.abs(posA - posB);

			double contribution = penalty * posB;
			//System.out.println("Contribution " + title.getTitle() + " = " + contribution);
			dOrderMaxA += contribution;
		}
		this.dOrderMaxA = dOrderMaxA;
		
		for (Title title : commonTitles) {
			// La posición en A es i
			// La peor posición en B sería n - i
			double posB = title.getPositionSource2();
			double posA = n - posB;

			double penalty = 1.0 / Math.log(1 + posB);
			//double distance = Math.abs(posB - posA);

			double contribution = penalty * posA;
			//System.out.println("Contribution " + title.getTitle() + " = " + contribution);
			dOrderMaxB += contribution;
		}
		this.dOrderMaxB = dOrderMaxB;

		return (dOrderMaxA + dOrderMaxB) / 2;
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
		BigDecimal dOrderARound = new BigDecimal(dOrderA).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal dOrderBRound = new BigDecimal(dOrderB).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal dOrderRound = new BigDecimal(dOrder).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal dOrderMaxARound = new BigDecimal(dOrderMaxA).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal dOrderMaxBRound = new BigDecimal(dOrderMaxB).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal dOrderMaxRound = new BigDecimal(dOrderMax).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal valueRound = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		String explanation = "<h1>Calculation of <b>Order Index</b></h1>";
		explanation += "<div class=\"divider\"></div>";
		
		// 0
		explanation += "<br/><h2>Top calculated:</h2>";
		explanation += "\\[n = " + n + "\\]";
		
		// 1
		explanation += "<br/><h2>1. Determine the intersection of elements between both lists for the top calculated.</h2>";
		explanation += "\\[k = " + k + " \\]";
		int i=0;
		for (Title title : commonTitles) {
			i++;
			explanation += " - " + title.getTitle() + "<br/>";
			if (i>10) { 
				explanation += "... <br/>";
				break;
			}
		}
		
		// 2
		explanation += "<br/><br/><h2>2. Calculate the contribution of each title and aggregate results.</h2>";
		explanation += "The contribution is calculated by using the distance between elements in both lists and a weight that Penalizes ranking misalignment by applying a logarithmic weight that prioritizes variance in top-tier positions.";
		explanation += "\\[ D_{orden}(A, B) = \\sum_{e \\in A \\cap B} \\frac{|pos_A(e) - pos_B(e)|}{\\ln(1 + pos_A(e))} = " + dOrderARound.toPlainString() + " \\]"; 
		explanation += "<br/>\\[ D_{orden}(B, A) = \\sum_{e \\in A \\cap B} \\frac{|pos_B(e) - pos_A(e)|}{\\ln(1 + pos_B(e))} = " + dOrderBRound.toPlainString() + " \\]"; 
		explanation += "<br/>\\[ D_{orden}^{sym} = \\frac{D_{orden}(A, B) + D_{orden}(B, A)}{2} = " + dOrderRound.toPlainString() + " \\]";
		
		// 3
		explanation += "<br/><br/><h2>3. Calculate the worst order case possible.</h2>";
		explanation += "The theoretical maximum for ordinal difference is defined as the worst-case ranking misalignment within the comparison universe, considering the total list size.";
		explanation += "\\[ D_{orden}^{max}(A, B) = \\sum_{e \\in A \\cap B} \\frac{n - pos_A(e)}{\\ln(1 + pos_A(e))} \\]";
		explanation += "<br/>\\[ D_{orden}^{max}(B, A) = \\sum_{e \\in A \\cap B} \\frac{n - pos_B(e)}{\\ln(1 + pos_B(e))} \\]";
		explanation += "<br/>\\[ D_{orden}^{max, sym} = \\frac{D_{orden}^{max}(A, B) + D_{orden}^{max}(B, A)}{2} = \\frac{" + dOrderMaxARound.toPlainString() + " + " + dOrderMaxBRound.toPlainString() + "}{2} = " + dOrderMaxRound.toPlainString() + "\\]";
		
		// 4
		explanation += "<br/><br/><h2>4. Calculate the Order Index by normalizing the Order.</h2>";
		explanation += "\\[ I_{orden} = \\frac{D_{orden}^{sym}}{D_{orden}^{max,sym}} = " + dOrderRound.toPlainString() + " + " + dOrderMaxRound.toPlainString() + " = " + valueRound.toPlainString() + "\\]";
		
		return explanation;
	}
}
