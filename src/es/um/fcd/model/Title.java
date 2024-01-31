package es.um.fcd.model;

import java.io.Serializable;
import java.text.Normalizer;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.apache.commons.text.similarity.LevenshteinDistance;

import es.um.fcd.util.Strings;

@Entity(name="TITLE")
public class Title implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;	
	private String title;
	private int position;
	private Source source;

	public Title() {
		super();
	}
	
	public Title(String title, int position, Source source) {
		this.title = title;
		this.position = position;
		this.source = source;
	}
	
	public Title(String title, int position) {
		this.title = title;
		this.position = position;
		this.source = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public Source getSource() {
		return source;
	}

	public void setSource(Source source) {
		this.source = source;
	}
	
	@Override
	// Two strings will be considered equal if they match at least 90%
	public boolean equals(Object obj) {
		int lenght = title.length();
		int thresholdDistance = Math.round((10*lenght)/100);
		if (thresholdDistance == 0) thresholdDistance = 1;
		String titleCompared = ((Title)obj).getTitle();
		String title1 = Strings.removeAccents(title.toLowerCase());
		String title2 = Strings.removeAccents(titleCompared.toLowerCase());
		System.out.println("Comparing: " + title1 + "|" + title2 + " -- Thresshold: " + thresholdDistance);
		int similarity = Strings.getSimilarity(title1, title2);
		if (similarity <= thresholdDistance) return true;
		return false;
	}
	

}
