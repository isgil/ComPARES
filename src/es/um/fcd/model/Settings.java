package es.um.fcd.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

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
	
	Map<String, Object> settings;

	public Settings() {
		super();
		settings = new HashMap<String, Object>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Map<String, Object> getSettings() {
		return settings;
	}

	public void setTopConfiguration(Map<String, Object> settings) {
		this.settings = settings;
	}
}
