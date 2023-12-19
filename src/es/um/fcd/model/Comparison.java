package es.um.fcd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="PROJECT")
public class Comparison implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<Par> pares;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Comparison() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Par> getPares(){
		return this.pares;
	}
	
	public void setPares(List<Par> pares) {
		this.pares = pares;
	}
}
