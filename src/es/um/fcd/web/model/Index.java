package es.um.fcd.web.model;

public abstract class Index {
	double value;
	
	public Index() {}
	
	public Index(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
}
