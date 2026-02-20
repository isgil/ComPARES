package es.um.fcd.web.model;

public class TopResultDetailed {
	private OrderIndex orderIndex;
	private AbsenceIndex absenceIndex;
	private CombinedIndex combinedIndex;
	private GSFnIndex GSFnIndex;
	
	public TopResultDetailed(OrderIndex orderIndex, AbsenceIndex absenceIndex, CombinedIndex combinedIndex, GSFnIndex GSFnIndex) {
		this.orderIndex = orderIndex;
		this.absenceIndex = absenceIndex;
		this.combinedIndex = combinedIndex;
		this.GSFnIndex = GSFnIndex;
	}

	public OrderIndex getOrderIndex() {
		return orderIndex;
	}

	public void setOrderIndex(OrderIndex orderIndex) {
		this.orderIndex = orderIndex;
	}

	public AbsenceIndex getAbsenceIndex() {
		return absenceIndex;
	}

	public void setAbsenceIndex(AbsenceIndex absenceIndex) {
		this.absenceIndex = absenceIndex;
	}

	public CombinedIndex getCombinedIndex() {
		return combinedIndex;
	}

	public void setCombinedIndex(CombinedIndex combinedIndex) {
		this.combinedIndex = combinedIndex;
	}
	
	public GSFnIndex getGSFnIndex() {
		return GSFnIndex;
	}

	public void setGSFnIndex(GSFnIndex GSFnIndex) {
		this.GSFnIndex = GSFnIndex;
	}
}