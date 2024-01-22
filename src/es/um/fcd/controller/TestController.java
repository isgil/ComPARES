package es.um.fcd.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.TestFile;

public class TestController {
	private static TestController instancia = null;

	private TestController() {
		// seccionesLeidas = new LinkedList<String>();
	}

	public static TestController getInstancia() {
		if (instancia == null)
			instancia = new TestController();
		return instancia;
	}

	public List<String> getTitles(TestFile testFile) throws DAOException, FileNotFoundException, IOException {
		String extension = testFile.getExtension();
		TestFileStrategy tfs = null;
		List<String> titles = null;
		if (extension.equalsIgnoreCase("txt")) {
			tfs = new TestFileStrategyTXT();
			titles = tfs.getTitles(testFile);
		} else if (extension.equalsIgnoreCase("xml")) {
			tfs = new TestFileStrategyXML();
			titles = tfs.getTitles(testFile);
		} else {
			throw new IOException("File extension not recognized");
		}
		
		return titles;
	}
}