package es.um.fcd.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.TestFile;
import es.um.fcd.model.Title;

public class TestFileStrategyTXT extends TestFileStrategy {

	@Override
	protected List<Title> extractTitles(TestFile testFile, String customTitleMark) throws DAOException, FileNotFoundException, IOException {
		Map<String, Title> titles = new LinkedHashMap<String, Title>();
		FileReader file = null;
		String titleMark = (customTitleMark != null && !customTitleMark.isEmpty()) ? customTitleMark : "title";
		System.out.println("Title mark=" + titleMark);
	
		file = new FileReader(testFile.getFullPhysicalName());
		BufferedReader buff = new BufferedReader(file);
		String line = "";
		while ((line = buff.readLine()) != null) {
			if (line.contains(titleMark)) {
				String titleStr = line.replaceFirst("\\s*" + titleMark + "\\s*[^A-Za-z0-9]\\s*", "");
				titleStr = titleStr.replaceAll("</?[^>]+>", "");
				titleStr = titleStr.replaceAll("[^\\p{L}\\p{ASCII}]", "");
				Title title = new Title(titleStr);
				titles.put(titleStr.toLowerCase(), title);
			}
		}
		file.close();
		List<Title> listOfTitles = new LinkedList<Title>(titles.values());

		return listOfTitles;
	}

}
