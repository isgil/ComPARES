package es.um.fcd.web.model;

import java.util.LinkedList;
import java.util.List;

public class Notificaciones {
	/*
	 * Definir en un punto central como este todas las reglas es una primera
	 * aproximación para internacionalizar las notificaciones
	 */	
	private List<String> exitos;
	private List<String> errores;
	private List<String> advertencias;

	public Notificaciones() {
		this.exitos = new LinkedList<String>();
		this.errores = new LinkedList<String>();
		this.advertencias = new LinkedList<String>();
	}

	public List<String> getExitos() {
		return exitos;
	}

	public void setExitos(List<String> exitos) {
		this.exitos = exitos;
	}

	public List<String> getErrores() {
		return errores;
	}

	public void setErrores(List<String> errores) {
		this.errores = errores;
	}

	public List<String> getAdvertencias() {
		return advertencias;
	}

	public void setAdvertencias(List<String> advertencias) {
		this.advertencias = advertencias;
	}

	// Añade el mensaje de éxito al principio
	public void addFirstExito(String exito) {
		if (!this.errores.contains(exito)) {
			this.exitos.add(0, exito);
		}
	}

	public void addExito(String exito) {
		if (!this.errores.contains(exito)) {
			this.exitos.add(exito);
		}
	}

	public String popExito() {
		if (this.exitos.isEmpty())
			return null;
		String mensaje = this.exitos.get(0);
		this.exitos.remove(0);
		return mensaje;
	}

	public List<String> popAllExito() {
		List<String> pop = new LinkedList<String>(this.exitos);
		this.exitos = new LinkedList<String>();
		return pop;
	}

	// Añade el mensaje de error al principio
	public void addFirstError(String error) {
		if (!this.errores.contains(error)) {
			this.errores.add(0, error);
		}
	}

	public void addError(String error) {
		if (!this.errores.contains(error)) {
			this.errores.add(error);
		}
	}

	public String popError() {
		if (this.errores.isEmpty())
			return null;
		String mensaje = this.errores.get(0);
		this.errores.remove(0);
		return mensaje;
	}

	public List<String> popAllError() {
		List<String> pop = new LinkedList<String>(this.errores);
		this.errores = new LinkedList<String>();
		return pop;
	}

	// Añade un mensaje de advertencia al principio
	public void addFirstAdvertencia(String advertencia) {
		if (!this.errores.contains(advertencia)) {
			this.advertencias.add(0, advertencia);
		}
	}

	public void addAdvertencia(String advertencia) {
		if (!this.errores.contains(advertencia)) {
			this.advertencias.add(advertencia);
		}
	}

	public String popAdvertencia() {
		if (this.advertencias.isEmpty())
			return null;
		String mensaje = this.advertencias.get(0);
		this.advertencias.remove(0);
		return mensaje;
	}

	public List<String> popAllAdvertencia() {
		List<String> pop = new LinkedList<String>(this.advertencias);
		this.advertencias = new LinkedList<String>();
		return pop;
	}
}
