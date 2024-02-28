package es.um.fcd.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.TestFile;
import es.um.fcd.model.Title;

public class TestFileStrategyRIS extends TestFileStrategy {

	@Override
	protected List<Title> extractTitles(TestFile testFile) throws DAOException, FileNotFoundException, IOException {
		List<Title> titles = new LinkedList<Title>();
		FileReader file = null;
	
		file = new FileReader(testFile.getFullPhysicalName());
		BufferedReader buff = new BufferedReader(file);
		String line = "";
		int position = 0;
		while ((line = buff.readLine()) != null) {
			if (line.contains("TI  - ")) {
				String titleStr = line.replaceFirst("TI  - ", "");
				Title title = new Title(titleStr, position);
				titles.add(title);
				position++;
			}
		}
		file.close();

		return titles;
	}
}
