package es.um.fcd.web.model;

public class TopResult {
	private double orderIndex;
	private double absenceIndex;
	private double combinedIndex;
	private double GSFnIndex;
	
	public TopResult(double orderIndex, double absenceIndex, double combinedIndex, double GSFnIndex) {
		this.orderIndex = orderIndex;
		this.absenceIndex = absenceIndex;
		this.combinedIndex = combinedIndex;
		this.GSFnIndex = GSFnIndex;
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
	
	public double getGSFnIndex() {
		return GSFnIndex;
	}

	public void setGSFnIndex(double GSFnIndex) {
		this.GSFnIndex = GSFnIndex;
	}
}