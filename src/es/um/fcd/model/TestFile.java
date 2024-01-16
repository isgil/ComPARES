package es.um.fcd.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name="TEST_FILE")
public class TestFile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String fileName;
	private String physicalFileName;
	private String extension;
	private String path;
	
	public TestFile() {
		super();
		physicalFileName = UUID.randomUUID().toString();
	}

	public TestFile(String fileName, String path) {
		this.setFileName(fileName);
		this.physicalFileName = UUID.randomUUID().toString();
		this.extension = fileName.substring(fileName.lastIndexOf("."));
		this.path = path;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPhysicalFileName() {
		return physicalFileName;
	}

	public void setPhysicalFileName(String physicalFileName) {
		this.physicalFileName = physicalFileName;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public String getFullPhysicalName() {
		return path + "/" + physicalFileName + "." + extension;
	}
	
	public String getFullName() {
		return path + "/" + fileName + "." + extension;
	}
}
