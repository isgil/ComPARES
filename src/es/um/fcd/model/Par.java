package es.um.fcd.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="PAR")
public class Par implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String file1;
	private String file2;
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
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
}
