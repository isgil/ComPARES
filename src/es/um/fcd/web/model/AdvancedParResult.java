package es.um.fcd.web.model;

import es.um.fcd.model.Par;

public class AdvancedParResult {
	private Par par;
	private int matching;
	private int samePosition;
	private double proximity;
	
	public AdvancedParResult(Par par) {
		this.par = par;
		this.matching = 0;
		this.samePosition = 0;
		this.proximity = 0;
	}
	
	public AdvancedParResult(Par par, int matching, int samePosition, double proximity) {
		this.par = par;
		this.matching = matching;
		this.samePosition = samePosition;
		this.proximity = proximity;
	}
	
	public Par getPar() {
		return par;
	}
	
	public void setPar(Par par) {
		this.par = par;
	}

	public int getMatching() {
		return matching;
	}

	public void setMatching(int matching) {
		this.matching = matching;
	}
	
	public void addMatching() {
		this.matching++;
	}

	public int getSamePosition() {
		return samePosition;
	}

	public void setSamePosition(int samePosition) {
		this.samePosition = samePosition;
	}
	
	public void addSamePosition() {
		this.samePosition++;
	}

	public double getProximity() {
		return proximity;
	}

	public void setProximity(double proximity) {
		this.proximity = proximity;
	}
}