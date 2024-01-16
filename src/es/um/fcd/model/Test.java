package es.um.fcd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name="TEST")
public class Test implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	//@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private Source source1;
	//@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private Source source2;
	private String titleMark1;
	private String titleMark2;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinTable(
	        name = "REL_TEST_PAR",
	        joinColumns = @JoinColumn(name = "TEST_ID"),
	        inverseJoinColumns = @JoinColumn(name = "PAR_ID")
	)
	private List<Par> pares;
	
	public Test() {
		super();
	}

	public Test(String name, Source source1, Source source2, String titleMark1, String titleMark2, List<Par> pares) {
		this.name = name;
		this.source1 = source1;
		this.source2 = source2;
		this.titleMark1 = titleMark1;
		this.titleMark2 = titleMark2;
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
	
	public Source getSource1() {
		return source1;
	}

	public void setSource1(Source source1) {
		this.source1 = source1;
	}

	public Source getSource2() {
		return source2;
	}

	public void setSource2(Source source2) {
		this.source2 = source2;
	}
	
	public String getTitleMark1() {
		return titleMark1;
	}

	public void setTitleMark1(String titleMark1) {
		this.titleMark1 = titleMark1;
	}

	public String getTitleMark2() {
		return titleMark2;
	}

	public void setTitleMark2(String titleMark2) {
		this.titleMark2 = titleMark2;
	}
	
	public List<Par> getPares(){
		return this.pares;
	}
	
	public void setPares(List<Par> pares) {
		this.pares = pares;
	}
}
