package es.um.fcd.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity(name="PAR")
public class Par implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private TestFile testFile1;
	@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private TestFile testFile2;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Par() {
		super();
	}

	public Par(TestFile testFile1, TestFile testFile2){
		this.setTestFile1(testFile1);
		this.setTestFile2(testFile2);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TestFile getTestFile1() {
		return testFile1;
	}

	public void setTestFile1(TestFile testFile1) {
		this.testFile1 = testFile1;
	}

	public TestFile getTestFile2() {
		return testFile2;
	}

	public void setTestFile2(TestFile testFile2) {
		this.testFile2 = testFile2;
	}	
}
