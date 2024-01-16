package es.um.fcd.web.controller;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import es.um.fcd.controller.FacadeSources;
import es.um.fcd.controller.FacadeTests;
import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.Source;
import es.um.fcd.model.TestFile;
import es.um.fcd.util.AppLogger;
import es.um.fcd.web.model.Notifications;
import es.um.fcd.web.util.FileLoader;

public class ActionNewTest extends Action {
	private String filesDir = "files";

	public String execute(HttpServletRequest request, HttpServletResponse response, ServletContext application) {
		if (request.getMethod().equalsIgnoreCase("POST")) {
			Notifications notifications = getNotificationsSession(request.getSession());
			
			String testName, source1, source2, titleMark1, titleMark2;
			testName = source1 = source2 = titleMark1 = titleMark2 = null;
			String filesDirectory = application.getRealPath("") + File.separator + filesDir;
			ServletFileUpload upload = FileLoader.getUpload(filesDirectory);
			List<TestFile> testFilesSource1 = new LinkedList<TestFile>();
			List<TestFile> testFilesSource2 = new LinkedList<TestFile>();

			try {
				List<FileItem> formItems = upload.parseRequest(request);
				Iterator<FileItem> iter = formItems.iterator();
				List<String> filesNotSaved = new LinkedList<String>();
				// Se recorren todos los campos
				while (iter.hasNext()) {
					FileItem item = (FileItem) iter.next();
					TestFile testFile = null;
					try {
						if (!item.isFormField()) {
							String fileName = new File(item.getName()).getName();
							testFile = new TestFile(fileName, filesDirectory);
							FileLoader.saveFile(item, testFile.getFullPhysicalName());
							if (item.getFieldName().equals("upload-input1")) {
								testFilesSource1.add(testFile);
							} else if (item.getFieldName().equals("upload-input2")) {
								testFilesSource2.add(testFile);
							}
						}
					} catch (Exception e) {
						// Si se produce algún error, se elimina el fichero subido
						// si ya se ha almacenado en el sistema
						AppLogger.logException(e);
						File file = new File(testFile.getFullPhysicalName());
						if (file.exists())
							file.delete();
						filesNotSaved.add(testFile.getFileName());
	
						continue;
					}
					
					String itemValue = item.getString();
					if (item.getFieldName().equals("test-name")) {
						testName = itemValue;
					}
					else if (item.getFieldName().equals("source1")) {
						source1 = itemValue;
					}
					else if (item.getFieldName().equals("source2")) {
						source2 = itemValue;
					}
					else if (item.getFieldName().equals("title-mark1")) {
						titleMark1 = itemValue;
					}
					else if (item.getFieldName().equals("title-mark2")) {
						titleMark2 = itemValue;
					}
				}
			}
			catch(FileUploadException e) {
				e.printStackTrace();
				response.setStatus(500);
				notifications.getError().add(Notifications.ERROR_CREATING_TEST);
				return new ActionLibrary().execute(request, response, application);
			}
			
			/* Creating new Test */
			// Get Sources
			FacadeSources fcSources = FacadeSources.getInstancia();
			Source source1db = null;
			Source source2db = null;
			try {
				source1db = fcSources.get(Integer.parseInt(source1));
			} catch (NumberFormatException | DAOException e) {
				notifications.getError().add(Notifications.ERROR_SOURCE_1);
				e.printStackTrace();
				response.setStatus(500);
				return new ActionLibrary().execute(request, response, application);
			}
			try {
				source2db = fcSources.get(Integer.parseInt(source2));
			} catch (NumberFormatException | DAOException e) {
				notifications.getError().add(Notifications.ERROR_SOURCE_2);
				e.printStackTrace();
				response.setStatus(500);
				return new ActionLibrary().execute(request, response, application);
			}
			
			// Create Pares
			List<Par> pares = new LinkedList<Par>();
			for (int i=0; i<testFilesSource1.size(); i++) {
				TestFile testFileSource1 = testFilesSource1.get(i);
				TestFile testFileSource2 = testFilesSource2.get(i);
				Par par = new Par(testFileSource1, testFileSource2);
				pares.add(par);
			}
			
			// Create test
			try {
				FacadeTests fcTests = FacadeTests.getInstancia();
				fcTests.add(testName, source1db, source2db, titleMark1, titleMark2, pares);
				notifications.getSuccess().add(Notifications.SUCCESS_TEST_CREATED);
			} catch (DAOException e) {
				response.setStatus(500);
				notifications.getError().add(Notifications.ERROR_CREATING_TEST);
				e.printStackTrace();
			}
			//request.setAttribute("test", test);
			response.setStatus(200);
			return new ActionLibrary().execute(request, response, application);
		} else {
			FacadeSources fcSources = FacadeSources.getInstancia();
			try {
				List<Source> sources = (List<Source>) fcSources.getAll();
				request.setAttribute("sources", sources);
				response.setStatus(200);
			} catch (DAOException e) {
				response.setStatus(500);
				e.printStackTrace();
			}
			System.out.println("returning new-test.jsp");
			return "/WEB-INF/views/new-test.jsp";
		}
	}
}