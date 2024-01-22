package es.um.fcd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity(name="PAR")
public class Par implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinColumn(name="TEST_FILE_SOURCE1")
	private TestFile testFileSource1;
	@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JoinColumn(name="TEST_FILE_SOURCE2")
	private TestFile testFileSource2;
	@Column(name="TITLES_SOURCE1")
	private List<String> titlesSource1;
	@Column(name="TITLES_SOURCE2")
	private List<String> titlesSource2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Par() {
		super();
	}

	public Par(TestFile testFileSource1, List<String> titlesSource1, TestFile testFileSource2, List<String> titlesSource2){
		this.testFileSource1 = testFileSource1;
		this.titlesSource1 = titlesSource1;
		this.testFileSource2 = testFileSource2;
		this.titlesSource2 = titlesSource2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TestFile getTestFileSource1() {
		return testFileSource1;
	}

	public void setTestFileSource1(TestFile testFileSource1) {
		this.testFileSource1 = testFileSource1;
	}

	public TestFile getTestFileSource2() {
		return testFileSource2;
	}

	public void setTestFileSource2(TestFile testFileSource2) {
		this.testFileSource2 = testFileSource2;
	}

	public List<String> getTitlesSource1() {
		return titlesSource1;
	}

	public void setTitlesSource1(List<String> titlesSource1) {
		this.titlesSource1 = titlesSource1;
	}

	public List<String> getTitlesSource2() {
		return titlesSource2;
	}

	public void setTitlesSource2(List<String> titlesSource2) {
		this.titlesSource2 = titlesSource2;
	}	
}
