package es.um.fcd.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
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
import javax.persistence.Table;

@Entity(name="PAR")
public class Par implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinColumn(name="TEST_FILE_SOURCE1")
	private TestFile testFileSource1;
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinColumn(name="TEST_FILE_SOURCE2")
	private TestFile testFileSource2;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
	@JoinTable(
	        name = "REL_PAR_TITLE",
	        joinColumns = @JoinColumn(name = "PAR_ID"),
	        inverseJoinColumns = @JoinColumn(name = "TITLE_ID")
	)
	private List<Title> titles;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Par() {
		super();
	}

	public Par(TestFile testFileSource1, TestFile testFileSource2, List<Title> titles){
		this.testFileSource1 = testFileSource1;
		this.testFileSource2 = testFileSource2;
		this.titles = titles;
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
	
	public List<Title> getTitlesSource1() {
		List<Title> titlesSource1 = new LinkedList<Title>();
		for (Title title : titles) {
			if (title.getPositionSource1() != -1) {
				titlesSource1.add(title);
			}
		}
		
		return titlesSource1;
	}
	
	public int getNumTitlesSource1() {
		return getTitlesSource1().size();
	}
	
	public List<Title> getTitlesSource2() {
		List<Title> titlesSource2 = new LinkedList<Title>();
		for (Title title : titles) {
			if (title.getPositionSource2() != -1) {
				titlesSource2.add(title);
			}
		}
		
		return titlesSource2;
	}
	
	public int getNumTitlesSource2() {
		return getTitlesSource2().size();
	}
/*
	public List<Title> getTitles(Source source) {
		List<Title> titlesSource = new LinkedList<Title>();
		for (Title title : titles) {
			if (title.getSource() == source) {
				titlesSource.add(title);
			}
		}
		return titlesSource;
	}
	
	public List<Title> getTitlesSorted(Source source) {
		List<Title> titlesSource = getTitles(source);
		Collections.sort(titlesSource, Comparator.comparing(Title::getPosition));
		
		return titlesSource;
	}
*/
	public List<Title> getTitles() {
		return titles;
	}

	public void setTitles(List<Title> titles) {
		this.titles = titles;
	}
}
