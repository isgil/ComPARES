package es.um.fcd.web.model;

import java.util.LinkedList;
import java.util.List;

import es.um.fcd.util.Strings;

public class Notifications {
	public static final String ERROR_NO_DOCUMENT_SELECTED = "No document selected";
	public static final String ERROR_UPLOAD = "Error during file upload process";
	public static final String ERROR_NUMBER_OF_FILES_MISMATCH = "The number of files for Source 1 and Source 2 do not match";
	public static final String ERROR_SOURCE_INCORRECT = "The source was not determined properly";
	public static final String ERROR_RETRIEVING_SOURCE = "The source could not be retrieved from the database";
	public static final String ERROR_CREATING_TEST = "Error during the creation of the test";
	public static final String SUCCESS_TEST_CREATED = "Test created successfully";
	public static final String ERROR_SOURCE_1 = "Error while processing Source 1";
	public static final String ERROR_SOURCE_2 = "Error while processing Source 2";
	
	public static final String getErrorFileUpload(List<String> filesWithUploadErrors) {
		return "The following documents could not be uploaded to the system: " + Strings.getString(filesWithUploadErrors, ",", true);
	}

	public static String getExitoFicherosSubidos(int filesUploadedSuccessfully) {
		return filesUploadedSuccessfully + " files were uploaded successfully";
	}
	
	/*
	 * Definir en un punto central como este todas las reglas es una primera
	 * aproximación para internacionalizar las notificaciones
	 */	
	private List<String> success;
	private List<String> error;
	private List<String> warning;

	public Notifications() {
		this.success = new LinkedList<String>();
		this.error = new LinkedList<String>();
		this.warning = new LinkedList<String>();
	}

	public List<String> getSuccess() {
		return success;
	}

	public void setSuccess(List<String> exitos) {
		this.success = exitos;
	}

	public List<String> getError() {
		return error;
	}

	public void setError(List<String> errores) {
		this.error = errores;
	}

	public List<String> getWarning() {
		return warning;
	}

	public void setWarning(List<String> advertencias) {
		this.warning = advertencias;
	}

	// Adds success prefix
	public void pushFirstSuccess(String exito) {
		if (!this.error.contains(exito)) {
			this.success.add(0, exito);
		}
	}

	public void pushSuccess(String exito) {
		if (!this.error.contains(exito)) {
			this.success.add(exito);
		}
	}

	public String popSuccess() {
		if (this.success.isEmpty())
			return null;
		String mensaje = this.success.get(0);
		this.success.remove(0);
		return mensaje;
	}

	public List<String> popAllSuccess() {
		List<String> pop = new LinkedList<String>(this.success);
		this.success = new LinkedList<String>();
		return pop;
	}

	// Añade el mensaje de error al principio
	public void pushFirstError(String error) {
		if (!this.error.contains(error)) {
			this.error.add(0, error);
		}
	}

	public void pushError(String error) {
		if (!this.error.contains(error)) {
			this.error.add(error);
		}
	}

	public String popError() {
		if (this.error.isEmpty())
			return null;
		String mensaje = this.error.get(0);
		this.error.remove(0);
		return mensaje;
	}

	public List<String> popAllError() {
		List<String> pop = new LinkedList<String>(this.error);
		this.error = new LinkedList<String>();
		return pop;
	}

	// Añade un mensaje de advertencia al principio
	public void pushFirstWarning(String advertencia) {
		if (!this.error.contains(advertencia)) {
			this.warning.add(0, advertencia);
		}
	}

	public void pushWarning(String advertencia) {
		if (!this.error.contains(advertencia)) {
			this.warning.add(advertencia);
		}
	}

	public String popWarning() {
		if (this.warning.isEmpty())
			return null;
		String mensaje = this.warning.get(0);
		this.warning.remove(0);
		return mensaje;
	}

	public List<String> popAllWarning() {
		List<String> pop = new LinkedList<String>(this.warning);
		this.warning = new LinkedList<String>();
		return pop;
	}
}
