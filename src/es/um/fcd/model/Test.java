package es.um.fcd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity(name="TEST")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	@OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	@JoinTable(
	        name = "REL_TEST_PAR",
	        joinColumns = @JoinColumn(name = "TEST_ID"),
	        inverseJoinColumns = @JoinColumn(name = "PAR_ID")
	)
	private List<Par> pares;
	
	public Test() {
		super();
	}

	public Test(String name, List<Par> pares) {
		this.name = name;
		this.pares = pares;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<Par> getPares(){
		return this.pares;
	}
	
	public void setPares(List<Par> pares) {
		this.pares = pares;
	}
}
