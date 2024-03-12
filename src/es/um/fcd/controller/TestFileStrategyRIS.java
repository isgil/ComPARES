package es.um.fcd.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
		//int position = 0;
		Pattern pattern = Pattern.compile("TI\\s+- (.*)");
		Matcher matcher = null;
		while ((line = buff.readLine()) != null) {
			matcher = pattern.matcher(line);
			if (matcher.find()) {
				String titleStr = matcher.group(1);
				titleStr = titleStr.replaceAll("</?[^>]+>", "");
				titleStr = titleStr.replaceAll("[^\\p{L}\\p{ASCII}]", "");
				Title title = new Title(titleStr);
				titles.add(title);
				//position++;
			}
		}
		file.close();

		return titles;
	}
}
