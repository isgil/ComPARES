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
	// Peor orden posible
	private double dOrderMax;
	// Suma de las distancias
	private double dOrder;

	public OrderIndex(int k, int n, List<Title> commonTitles) {
		super();
		this.k = k;
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

		double dOrdenA = 0.0;
		double dOrdenB = 0.0;

		for (Title title : commonTitles) {
			int posA = title.getPositionSource1();
			int posB = title.getPositionSource2();

			// Término: (1 / log(1 + posA)) * |posA - posB|
			double peso = 1.0 / Math.log(1 + posA);
			double diferenciaAbsoluta = Math.abs(posA - posB);

			dOrdenA += peso * diferenciaAbsoluta;
		}

		for (Title title : commonTitles) {
			int posA = title.getPositionSource1();
			int posB = title.getPositionSource2();

			// Término: (1 / log(1 + posA)) * |posA - posB|
			double peso = 1.0 / Math.log(1 + posB);
			double diferenciaAbsoluta = Math.abs(posB - posA);

			dOrdenB += peso * diferenciaAbsoluta;
		}

		return (dOrdenA + dOrdenB) / 2;
	}

	private double generateOrderMax(int n, List<Title> commonTitles) {
		double orderMaxA = 0.0;
		double orderMaxB = 0.0;

		for (Title title : commonTitles) {
			// La posición en A es i
			// La peor posición en B sería n - i + 1
			double posA = title.getPositionSource1();
			double posB = n - posA + 1;

			double peso = 1.0 / Math.log(1 + posA);
			double diferencia = Math.abs(posA - posB);

			orderMaxA += peso * diferencia;
		}
		
		for (Title title : commonTitles) {
			// La posición en A es i
			// La peor posición en B sería n - i + 1
			double posB = title.getPositionSource2();
			double posA = n - posB + 1;

			double peso = 1.0 / Math.log(1 + posB);
			double diferencia = Math.abs(posB - posA);

			orderMaxB += peso * diferencia;
		}

		return (orderMaxA + orderMaxB) / 2;
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
		BigDecimal dOrderMaxRound = new BigDecimal(dOrderMax).setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
		BigDecimal valueRound = new BigDecimal(value).setScale(2, RoundingMode.HALF_UP) .stripTrailingZeros();
		String explanation = "<h5>Calculation of <b>Order Index</b></h5>";
		explanation += "<div class=\"divider\"></div>";
		explanation += "<br/>Top treated: <b>n = " + n + "</b>";
		explanation += "<br/><br/>1. Identify all common elements between the lists for the top treated (k) = " + k;
		explanation += "<br/><br/>2. Calculate the contribution of each title and sum results: <span class=\"yellow lighten-4\">" + dOrder + "</span>";
		explanation += "<br/><br/>3. Calculate the worst order possible:";
		explanation += "<br/><b>Order(max) = k x (n-1)</b> = " + k + " x (" + n + " - 1) = <u><span class=\"yellow lighten-4\">" + dOrderMaxRound.toPlainString() + "</span></u>";
		explanation += "<br/><br/>4. Calculate the Order Index by standardizing order: ";
		explanation += "<br/><b>Order Index = Order / Order(max)</b> = " + dOrder + " / " + dOrderMaxRound.toPlainString() + " = " + "<u><span class=\"yellow lighten-4\">" + valueRound.toPlainString() + "</span></b>";
		
		
		return explanation;
	}
}
