package es.um.fcd.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.Test;
import es.um.fcd.model.TestFile;
import es.um.fcd.model.Title;
import es.um.fcd.web.model.ParResult;
import es.um.fcd.web.model.TestResult;

public class TestController {
	private static TestController instancia = null;
	private static int[] tops = {5, 10, 20, 50, 100, 200, 300, 400, 500, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000};

	private TestController() {
		// seccionesLeidas = new LinkedList<String>();
	}

	public static TestController getInstancia() {
		if (instancia == null)
			instancia = new TestController();
		return instancia;
	}
	
	private List<Title> getTitles(TestFile testFile) throws DAOException, FileNotFoundException, IOException {
		String extension = testFile.getExtension();
		TestFileStrategy tfs = null;
		List<Title> titles = null;
		if (extension.equalsIgnoreCase("txt")) {
			tfs = new TestFileStrategyTXT();
			titles = tfs.getTitles(testFile);
		} else if (extension.equalsIgnoreCase("ris")) {
			tfs = new TestFileStrategyRIS();
			titles = tfs.getTitles(testFile);
		} else if (extension.equalsIgnoreCase("bib")) {
			tfs = new TestFileStrategyBIB();
			titles = tfs.getTitles(testFile);
		} else {
			throw new IOException("File extension not recognized");
		}
		
		return titles;
	}

	public List<Title> getTitles(TestFile testFileSource1, TestFile testFileSource2) throws DAOException, FileNotFoundException, IOException {
		List<Title> titlesSource1 = getTitles(testFileSource1);
		List<Title> titlesSource2 = getTitles(testFileSource2);
		
		return processTitles(titlesSource1, titlesSource2);
	}
	
	private List<Title> processTitles(List<Title> titlesSource1, List<Title> titlesSource2) {
		List<Title> titles = new LinkedList<Title>();
		Set<Integer> titlesMatchedSource2 = new HashSet<Integer>();
		int positionSource1 = 1;
		for (Title title : titlesSource1) {
			int positionSource2 = titlesSource2.indexOf(title);
			if (positionSource2 != -1) {
				titlesMatchedSource2.add(positionSource2);
			}
			title.setPositionSource1(positionSource1);
			title.setPositionSource2(positionSource2);
			titles.add(title);
			positionSource1++;
		}
		int numTitlesSource2 = titlesSource2.size();
		for (int i=1; i<=numTitlesSource2; i++) {
			if (!titlesMatchedSource2.contains(i)) {
				Title title = titlesSource2.get(i-1);
				title.setPositionSource1(-1);
				title.setPositionSource2(i);
				titles.add(title);
			}
		}
		
		return titles;
	}
	
	public TestResult getTestResult(Test test) {
		List<Par> pares = test.getPares();
		List<ParResult> paresResults = new LinkedList<ParResult>();
		for (Par par : pares) {
			System.out.println("Calculating par " + par.getId());
			List<Title> titles = par.getTitles();
			int numTitlesSource1 = par.getTitlesSource1().size();
			int numTitlesSource2 = par.getTitlesSource2().size();
			int minNumTitles = (numTitlesSource1 <= numTitlesSource2) ? numTitlesSource1 : numTitlesSource2;
			Map<Integer, Integer> results = new LinkedHashMap<Integer, Integer>();
			double mean = 0;
			int numTops = 0;
			for (int top : tops) {
				if (top <= minNumTitles) {
					numTops++;
					System.out.println("Calculating top " + top);
					int accumulatedDistance = 0;
					for (Title title : titles) {
						int posSource1 = title.getPositionSource1();
						int posSource2 = title.getPositionSource2();
						int distance = 0;
						if ((posSource1 <= top && posSource1 != -1) /*|| (posSource2 <= top && posSource2 != -1)*/) {
							if (posSource1 > top || posSource2 > top) distance = top;
							else distance = Math.abs(posSource1 - posSource2);
							accumulatedDistance += distance;
							if (top == 5) 
							System.out.println("Top / Pos1 / Pos2 / Distance: " + top + "/" + posSource1 + " / " + posSource2 + " / " + distance);
						}
					}
					System.out.println("Total accumulated distance: " + accumulatedDistance);
					int proximity = 100 - (100 * accumulatedDistance) / (top * top);
					results.put(top, proximity);
					mean += proximity;
				} else {
					// No more tops to calculate
					break;
				}
			}
			mean = mean / numTops;
			ParResult parResult = new ParResult(par, results, mean);
			paresResults.add(parResult);
		}
		TestResult testResult = new TestResult(test, paresResults);
		
		return testResult;
	}
}