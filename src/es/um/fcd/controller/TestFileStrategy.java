package es.um.fcd.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.TestFile;

public abstract class TestFileStrategy {
	
	abstract protected List<String> extractTitles(TestFile testFile) throws DAOException, FileNotFoundException, IOException;

	public List<String> getTitles(TestFile testFile) throws DAOException, FileNotFoundException, IOException {
		List<String> titles = extractTitles(testFile);

		return titles;
	}
}
