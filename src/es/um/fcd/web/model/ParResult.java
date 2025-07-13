package es.um.fcd.web.model;

import es.um.fcd.model.Par;

public class ParResult {
	private Par par;
	private double orderIndex;
	private double absenceIndex;
	private double combinedIndex;
	
	public ParResult(Par par, double orderIndex, double absenceIndex, double combinedIndex) {
		this.par = par;
		this.orderIndex = orderIndex;
		this.absenceIndex = absenceIndex;
		this.combinedIndex = combinedIndex;
	}
	
	public Par getPar() {
		return par;
	}
	
	public void setPar(Par par) {
		this.par = par;
	}

	public double getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(double orderIndex) {
		this.orderIndex = orderIndex;
	}

	public double getAbsenceIndex() {
		return absenceIndex;
	}

	public void setAbsenceIndex(double absenceIndex) {
		this.absenceIndex = absenceIndex;
	}

	public double getCombinedIndex() {
		return combinedIndex;
	}

	public void setCombinedIndex(double combinedIndex) {
		this.combinedIndex = combinedIndex;
	}
}