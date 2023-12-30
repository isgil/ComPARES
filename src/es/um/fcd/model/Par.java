package es.um.fcd.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="PAR")
public class Par implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String fileName1;
	private String fileName2;
	private Source source1;
	private Source source2;
	private String titleMark1;
	private String titleMark2;
	private float top10;
	private float top50;
	private float top100;
	private float top1000;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	public Par() {
		super();
	}

	public Par(String fileName1, Source source1, String titleMark1, String fileName2, Source source2, String titleMark2){
		this.fileName1 = fileName1;
		this.source1 = source1;
		this.titleMark1 = titleMark1;
		this.titleMark2 = titleMark2;
		this.fileName2 = fileName2;
		this.source2 = source2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String file1) {
		this.fileName1 = file1;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String file2) {
		this.fileName2 = file2;
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

	public float getTop10() {
		return top10;
	}

	public void setTop10(float top10) {
		this.top10 = top10;
	}

	public float getTop50() {
		return top50;
	}

	public void setTop50(float top50) {
		this.top50 = top50;
	}

	public float getTop100() {
		return top100;
	}

	public void setTop100(float top100) {
		this.top100 = top100;
	}

	public float getTop1000() {
		return top1000;
	}

	public void setTop1000(float top1000) {
		this.top1000 = top1000;
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
}
