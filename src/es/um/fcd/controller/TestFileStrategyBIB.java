package es.um.fcd.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.TestFile;
import es.um.fcd.model.Title;

public class TestFileStrategyBIB extends TestFileStrategy {

	@Override
	protected List<Title> extractTitles(TestFile testFile, String customTitleMark) throws DAOException, FileNotFoundException, IOException {
		Map<String, Title> titles = new LinkedHashMap<String, Title>();
		FileReader file = null;
		String titleMark = (customTitleMark != null && !customTitleMark.isEmpty()) ? customTitleMark : "title";
		
	
		file = new FileReader(testFile.getFullPhysicalName());
		BufferedReader buff = new BufferedReader(file);
		String line = "";
		Pattern pattern = Pattern.compile("\\s+" + titleMark + " = \\{(.*)\\}");
		Matcher matcher = null;
		while ((line = buff.readLine()) != null) {
			matcher = pattern.matcher(line);
			if (matcher.find()) {
				String titleStr = matcher.group(1);
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
