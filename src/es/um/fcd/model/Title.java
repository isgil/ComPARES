package es.um.fcd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import es.um.fcd.util.Strings;

@Entity(name="TITLE")
public class Title implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 1000)
	private String title;
	private int positionSource1;
	private int positionSource2;

	public Title() {
		super();
	}
	
	public Title(String title) {
		this.title = title;
		this.positionSource1 = -1;
		this.positionSource2 = -1;
	}
	
	public Title(String title, int positionSource1, int positionSource2) {
		this.title = title;
		this.positionSource1 = positionSource1;
		this.positionSource2 = positionSource2;
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

	public int getPositionSource1() {
		return positionSource1;
	}

	public void setPositionSource1(int positionSource1) {
		this.positionSource1 = positionSource1;
	}
	
	public int getPositionSource2() {
		return positionSource2;
	}

	public void setPositionSource2(int positionSource2) {
		this.positionSource2 = positionSource2;
	}
	
	public int calculateDistance(int top) {
		return calculateDistance(top, -1);
	}
	
	public int calculateDistance(int top, int maxDistance) {
		if (this.positionSource1 == -1 || this.positionSource2 == -1) return maxDistance;
		else if (top != -1 && (this.positionSource1 > top || this.positionSource2 > top)) return maxDistance;
		else return Math.abs(this.positionSource1 - this.positionSource2);
	}
	
	public int calculateAbsoluteDistance(int top) {
		return calculateAbsoluteDistance(top, 0);
	}
	
	public int calculateAbsoluteDistance(int top, int maxDistance) {
		int posSource1 = this.positionSource1;
		int posSource2 = this.positionSource2;
		if (posSource1 == -1 || posSource1 > top) posSource1 = maxDistance;
		else if (posSource2 == -1 || posSource2 > top) posSource2 = maxDistance;
		int distance = Math.abs(posSource1 - posSource2);
		
		return distance;
	}

	@Override
	// Two strings will be considered equal if they match at least 90%
	public boolean equals(Object obj) {
		if (this != null && obj == null) return false;
		String titleCompared = ((Title)obj).getTitle();
		int lenghtThis = title.length();
		int lengthOther = titleCompared.length();
		int thresholdDistance = Math.round(lenghtThis/10);
		if (thresholdDistance == 0) thresholdDistance = 1;
		if (Math.abs(lenghtThis - lengthOther) > thresholdDistance) return false;
		String title1 = Strings.removeAccents(title.toLowerCase());
		String title2 = Strings.removeAccents(titleCompared.toLowerCase());
		//System.out.println("Comparing: " + title1 + "|" + title2 + " -- Thresshold: " + thresholdDistance);
		//if (title1.equalsIgnoreCase(title2)) return true;
		int similarity = Strings.getSimilarity(title1, title2);
		if (similarity <= thresholdDistance) return true;
		//if (similarity == 0) return true;
		return false;
	}
	

}
