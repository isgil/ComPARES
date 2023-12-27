package es.um.fcd.web.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import es.um.fcd.model.Par;
import es.um.fcd.model.Source;
import es.um.fcd.model.Test;
import es.um.fcd.util.AppLogger;
import es.um.fcd.web.controller.ActionNew;
import es.um.fcd.web.model.Notifications;
import es.um.fcd.web.util.FileLoader;

public class CargadorDocumentos extends CargadorFicheros {
	private static final long serialVersionUID = 1L;
	private final String documentsDir = "documents";

	@Override
	protected String getDirectorio() {
		return documentsDir;
	}

	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Notifications notifications = getSesionNotifications(request.getSession());

		// Checks if there are files attached
		if (!ServletFileUpload.isMultipartContent(request)) {
			notifications.pushError(Notifications.ERROR_NO_DOCUMENT_SELECTED);
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNew().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);

			return;
		}

		String dir = getServletContext().getRealPath("") + File.separator + directorioPath;
		ServletFileUpload upload = FileLoader.getUpload(dir);

		String fileName = null;
		String filePath = null;
		List<String> fileListSource1 = new ArrayList<String>();
		List<String> fileListSource2 = new ArrayList<String>();
		String fileNameUnique = null;
		String extension = null;
		String sourceStr1, sourceStr2;
		List<String> filesWithUploadErrors = new LinkedList<String>();

		// Preparamos la lista de campos que hemos recibido
		try {
			List formItems = upload.parseRequest(request);
			Iterator iter = formItems.iterator();

			// All form filds are inspected
			while (iter.hasNext()) {
				try {
					fileNameUnique = UUID.randomUUID().toString();
					FileItem item = (FileItem) iter.next();

					// File
					if (!item.isFormField() && item.getSize() > 0) {
						fileName = new File(item.getName()).getName();
						extension = fileName.substring(fileName.lastIndexOf("."));
						filePath = dir + File.separator + fileNameUnique + extension;
						FileLoader.saveFile(item, filePath);
						if (item.getFieldName().equalsIgnoreCase("filesSource1")) {
							fileListSource1.add(filePath);
						} else {
							fileListSource2.add(filePath);
						}
					}
					// Source 1
					else if (item.isFormField() && item.getFieldName().equalsIgnoreCase("source1")) {
						sourceStr1 = item.getString();
					}
					// Source 1
					else if (item.isFormField() && item.getFieldName().equalsIgnoreCase("source2")) {
						sourceStr2 = item.getString();
					}
				} catch (Exception e) {
					// Remove file if there was an error uploading
					AppLogger.logException(e);
					File file = new File(filePath);
					if (file.exists())
						file.delete();
					filesWithUploadErrors.add(fileName);

					continue;
				}
			}

			if (filesWithUploadErrors.size() > 0) {
				notifications.pushError(Notifications.getErrorFileUpload(filesWithUploadErrors));
			}
		} catch (Exception e) {
			AppLogger.logException(e);
			notifications.pushError(Notifications.ERROR_UPLOAD);
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNew().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
			
			return;
		}
		// If number of files for Source 1 is not the same than for Source 2, throw error.
		if (fileListSource1.size() != fileListSource2.size()) {
			notifications.pushError(Notifications.ERROR_NUMBER_OF_FILES_MISMATCH);
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNew().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
			
			return;
		}

		List<Integer> filesWithDBErrors = new LinkedList<Integer>();
		// Se preprocesan los documentos
		int fileCounter = 0;
		//request.getSession().setAttribute("numDocumentosParaSubir", listaFicheros.size());
		
		int numFiles = fileListSource1.size();
		Test test = new Test();
		List<Par> pares = new LinkedList<Par>();
		Source source1 = new Source(sourceStr1);
		Source source2 = new Source(sourceStr2);
		for (int i=0; i<numFiles; i++) {
			Par par = new Par();
			try {
				request.getSession().setAttribute("progressAction", "Uploading");
				request.getSession().setAttribute("progressValue", i + "/" + numFiles);
				test = FachadaDocumentos.getInstancia().addDocumento(path, publico);
				}
				String idiomaIdentificado = determinarIdioma(doc, getServletConfig().getServletContext().getRealPath(""));
				if (idiomaIdentificado == null || idiomaIdentificado.isEmpty()) {
					notifications.pushError(Notifications.ADVERTENCIA_SIN_IDIOMA);
				}
				Idioma idioma = null;
				FachadaIdiomas fi = FachadaIdiomas.getInstancia();
				try{
					Idioma idiomaDB = fi.getIdioma(idiomaIdentificado);
					if (idiomaDB == null) {
						idioma = fi.addIdioma(idiomaIdentificado);
					} else {
						idioma = idiomaDB;
					}
				} catch (DAOException e) {
					AppLogger.logException(e);
					notifications.pushError(Notifications.ERROR_CARGAR_DOCUMENTO);
				}
				doc.setIdioma(idioma);
				FachadaDocumentos.getInstancia().update(doc);
				System.out.println("Idioma asignado al documento");
			} catch (Exception e) {
				// Si se produce algún error, se elimina el fichero subido
				// si ya se ha almacenado en el sistema
				AppLogger.logException(e);
				File file = new File(filePath);
				if (file.exists())
					file.delete();
				if (doc != null) {
					try {
						filesWithDBErrors.add(doc.getId());
						FachadaDocumentos.getInstancia().deleteDatosProcesados(doc.getId());
					} catch (Exception e1) {
						AppLogger.logException(e1);
						notifications.pushError(Notifications.getErrorEliminarDatosProcesados(doc.getId().toString()));
					}
				}
			}
		}
		request.getSession().removeAttribute("progressAction");
		request.getSession().removeAttribute("progressValue");
		
		//request.getSession().removeAttribute("documentoActual");
		//request.getSession().removeAttribute("numDocumentosParaSubir");
		
		if (filesWithUploadErrors.size() > 0) {
			notifications.pushError(Notifications.getErrorDocumentosBBDD(filesWithDBErrors));
		}

		// Reenviar a la vista
		if (fileList.size() > 0) {
			notifications.pushSuccess(Notifications.getExitoFicherosSubidos(fileList.size()));
			RequestDispatcher rd = request.getRequestDispatcher(new AccionDocumentos().ejecutar(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
		} else {
			if (filesWithUploadErrors.size() == 0){
				notifications.pushError(Notifications.ERROR_NO_DOCUMENT_SELECTED);
			}
			RequestDispatcher rd = request.getRequestDispatcher(new AccionDocumentoNuevo().ejecutar(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
		}
	}
	
	private String determinarIdioma(Documento documento, String applicationPath) throws IOException {
		FileInputStream inputStream = new FileInputStream(documento.getUri());
		String texto = "";
		try {
		    texto = IOUtils.toString(inputStream);
		    texto = EstrategiaTexto.limpiarTexto(texto);
		    /*int endIndex = texto.length();
		    if (endIndex - 1000 > 1000) endIndex = endIndex - 1000;
		    texto = texto.substring(0, endIndex);*/
		} finally {
		    inputStream.close();
		}
		
		System.out.println("Texto = " + texto);
		
		return determinarIdioma(texto, applicationPath);
	}

	private String determinarIdioma(String texto, String applicationPath) {
		System.out.println("Detectar idioma");
		String idiomaIdentificado = null;
		if (!texto.isEmpty()) {
			// Se captura el idioma
			try {
				idiomaIdentificado = DetectorIdioma.getInstancia(applicationPath).detectarIdioma(texto);
				System.out.println("Idioma identificado = " + idiomaIdentificado);
			} catch (LangDetectException | DAOException e) {
				AppLogger.logException(e);
			}
		}
		
		return idiomaIdentificado;
	}
}
