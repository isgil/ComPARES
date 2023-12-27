package es.um.fcd.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity(name="USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	/* Código de cada tipo de usuario */
	public static final int ADMIN = 2000;
	public static final int USUARIO = 100;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
//	@Column(unique = true, nullable = false)
	private String username;
	private String password;
	private String email;
	private int group;

	public User() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String nombre) {
		this.username = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public boolean isAdmin() {
		return (group >= ADMIN);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User otro = (User) obj;
		return id.equals(otro.getId());
	}
}
