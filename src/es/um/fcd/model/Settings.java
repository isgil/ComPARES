package es.um.fcd.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="SETTINGS")
public class Settings implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true, nullable = false)
	
	private boolean allTops;
	private List<Integer> topConfiguration;

	public Settings() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean isAllTops() {
		return allTops;
	}

	public void setAllTops(boolean allTops) {
		this.allTops = allTops;
	}

	public List<Integer> getTopConfiguration() {
		return topConfiguration;
	}

	public void setTopConfiguration(List<Integer> topConfiguration) {
		this.topConfiguration = topConfiguration;
	}
}
