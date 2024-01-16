package es.um.fcd.web.servlet;

import java.io.File;
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

import es.um.fcd.controller.FacadePares;
import es.um.fcd.controller.FacadeSources;
import es.um.fcd.controller.FacadeTests;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.Source;
import es.um.fcd.util.AppLogger;
import es.um.fcd.web.controller.ActionLibrary;
import es.um.fcd.web.controller.ActionNewTest;
import es.um.fcd.web.model.Notifications;
import es.um.fcd.web.util.FileLoader;

public class TestUploader extends FileUploader {
	private static final long serialVersionUID = 1L;
	private final String filesDir = "files";

	@Override
	protected String getDirectory() {
		return filesDir;
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("rawtypes")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TEST UPLOADER RUNNING");
		Notifications notifications = getSesionNotifications(request.getSession());

		/*
		// Checks if there are files attached
		if (!ServletFileUpload.isMultipartContent(request)) {
			notifications.pushError(Notifications.ERROR_NO_DOCUMENT_SELECTED);
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNewTest().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);

			return;
		}

		String dir = getServletContext().getRealPath("") + File.separator + filesDir;
		ServletFileUpload upload = FileLoader.getUpload(dir);

		String fileName = null;
		String filePath = null;
		List<String> fileListSource1 = new ArrayList<String>();
		List<String> fileListSource2 = new ArrayList<String>();
		String fileNameUnique = null;
		String extension = null;
		String testName = null, sourceName1 = null, sourceName2 = null;
		String titleMark1 = null, titleMark2 = null;
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
						if (item.getFieldName().equalsIgnoreCase("files-source1")) {
							fileListSource1.add(filePath);
						} else {
							fileListSource2.add(filePath);
						}
					}
					// Test Name
					else if (item.isFormField() && item.getFieldName().equalsIgnoreCase("test-name")) {
						testName = item.getString();
					}
					// Test Name
					else if (item.isFormField() && item.getFieldName().equalsIgnoreCase("title-mark1")) {
						titleMark1 = item.getString();
					}
					// Test Name
					else if (item.isFormField() && item.getFieldName().equalsIgnoreCase("title-mark2")) {
						titleMark2 = item.getString();
					}
					// Source 1
					else if (item.isFormField() && item.getFieldName().equalsIgnoreCase("source1")) {
						sourceName1 = item.getString();
					}
					// Source 2
					else if (item.isFormField() && item.getFieldName().equalsIgnoreCase("source2")) {
						sourceName2 = item.getString();
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
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNewTest().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
			
			return;
		}
		// If number of files for Source 1 is not the same than for Source 2, throw error.
		if (fileListSource1.size() != fileListSource2.size()) {
			notifications.pushError(Notifications.ERROR_NUMBER_OF_FILES_MISMATCH);
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNewTest().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
			
			return;
		}
		
		int numFiles = fileListSource1.size();
		List<Par> pares = new LinkedList<Par>();
		FacadeSources fcSource = FacadeSources.getInstancia();
		if (sourceName1 == null || sourceName2 == null) {
			notifications.pushError(Notifications.ERROR_SOURCE_INCORRECT);
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNewTest().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
			return;
		}
		Source source1, source2;
		try {
			source1 = fcSource.get(sourceName1);
			if (source1 == null) source1 = fcSource.add(sourceName1);
			source2 = fcSource.get(sourceName2);
			if (source2 == null) source2 = fcSource.add(sourceName2);
		} catch (DAOException e1) {
			e1.printStackTrace();
			notifications.pushError(Notifications.ERROR_RETRIEVING_SOURCE);
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNewTest().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
			return;
		}
		for (int i=0; i<numFiles; i++) {
			String fileName1 = fileListSource1.get(i);
			String fileName2 = fileListSource2.get(i);
			try {
				Par par = FacadePares.getInstancia().add(fileName1, source1, titleMark1, fileName2, source2, titleMark2);
				pares.add(par);
				request.getSession().setAttribute("progressAction", "Uploading");
				request.getSession().setAttribute("progressValue", i + "/" + numFiles);
			} catch (Exception e) {
				// Si se produce algún error, se elimina el fichero subido
				// si ya se ha almacenado en el sistema
				AppLogger.logException(e);
				File file = new File(fileName1);
				if (file.exists()) {
					file.delete();
					file = new File(fileName2);
					if (file.exists()) {
						file.delete();
					}
				}
			}
		}
		
		FacadeTests fcTests = FacadeTests.getInstancia();
		try {
			fcTests.add(testName, pares);
		} catch (DAOException e) {
			e.printStackTrace();
			notifications.pushError(Notifications.ERROR_CREATING_TEST);
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNewTest().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
			return;
		}
		
		request.getSession().removeAttribute("progressAction");
		request.getSession().removeAttribute("progressValue");

		// Reenviar a la vista
		if (numFiles > 0) {
			notifications.pushSuccess(Notifications.getExitoFicherosSubidos(numFiles - filesWithUploadErrors.size()));
			RequestDispatcher rd = request.getRequestDispatcher(new ActionLibrary().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
			return;
		} else {
			if (filesWithUploadErrors.size() == 0){
				notifications.pushError(Notifications.ERROR_NO_DOCUMENT_SELECTED);
			}
			RequestDispatcher rd = request.getRequestDispatcher(new ActionNewTest().execute(request, response, getServletConfig().getServletContext()));
			rd.forward(request, response);
			return;
		}
		*/
	}
}
