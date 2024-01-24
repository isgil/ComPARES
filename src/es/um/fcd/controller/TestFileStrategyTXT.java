package es.um.fcd.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.TestFile;

public class TestFileStrategyTXT extends TestFileStrategy {

	@Override
	protected List<String> extractTitles(TestFile testFile) throws DAOException, FileNotFoundException, IOException {
		List<String> titles = new LinkedList<String>();
		FileReader file = null;
	
		file = new FileReader(testFile.getFullPhysicalName());
		BufferedReader buff = new BufferedReader(file);
		String line = "";
		while ((line = buff.readLine()) != null) {
			if (line.contains("title")) {
				String title = line.replaceFirst("title=", "");
				titles.add(title);
			}
		}
		file.close();

		return titles;
	}

}
