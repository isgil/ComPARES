package es.um.fcd.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.TestFile;
import es.um.fcd.model.Title;

public abstract class TestFileStrategy {
	
	abstract protected List<Title> extractTitles(TestFile testFile, String titleMark) throws DAOException, FileNotFoundException, IOException;
}
