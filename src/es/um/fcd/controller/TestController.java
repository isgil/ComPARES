package es.um.fcd.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.Test;
import es.um.fcd.model.TestFile;
import es.um.fcd.model.Title;
import es.um.fcd.web.model.AdvancedParResult;
import es.um.fcd.web.model.ParResult;
import es.um.fcd.web.model.TestResult;
import es.um.fcd.web.model.TopResult;

public class TestController {
	private static TestController instancia = null;
	private static List<Integer> defaultTops = Arrays.asList(5, 10, 20, 50, 100, 200, 300, 400, 500, 1000, 1500, 2000, 2500, 3000, 3500, 4000, 4500, 5000, 5500, 6000, 6500, 7000, 7500, 8000, 8500, 9000, 9500, 10000);

	private TestController() {
		// seccionesLeidas = new LinkedList<String>();
	}

	public static TestController getInstancia() {
		if (instancia == null)
			instancia = new TestController();
		return instancia;
	}
	
	private List<Title> getTitles(TestFile testFile, String titleMark) throws DAOException, FileNotFoundException, IOException {
		String extension = testFile.getExtension();
		TestFileStrategy tfs = null;
		List<Title> titles = null;
		if (extension.equalsIgnoreCase("txt")) {
			tfs = new TestFileStrategyTXT();
			titles = tfs.extractTitles(testFile, titleMark);
		} else if (extension.equalsIgnoreCase("ris")) {
			tfs = new TestFileStrategyRIS();
			titles = tfs.extractTitles(testFile, titleMark);
		} else if (extension.equalsIgnoreCase("bib")) {
			tfs = new TestFileStrategyBIB();
			titles = tfs.extractTitles(testFile, titleMark);
		} else {
			throw new IOException("File extension not recognized");
		}
		
		System.out.println("Titles read: " + titles.size());
		/*for (Title title : titles) {
			System.out.println(title.getTitle());
		}*/
		
		return titles;
	}

	public List<Title> getTitles(TestFile testFileSource1, String titleMarkSource1, TestFile testFileSource2, String titleMarkSource2, HttpSession session) throws DAOException, FileNotFoundException, IOException {

		List<Title> titlesSource1 = getTitles(testFileSource1, titleMarkSource1);
		List<Title> titlesSource2 = getTitles(testFileSource2, titleMarkSource2);
		
		return processTitles(titlesSource1, titlesSource2, session);
	}
	
	private List<Title> processTitles(List<Title> titlesSource1, List<Title> titlesSource2, HttpSession session) {
		List<Title> titles = new LinkedList<Title>();
		int totalTitles = titlesSource1.size() + titlesSource2.size();
		int titlesProcessed = 0;
		Set<Integer> titlesMatchedSource2 = new HashSet<Integer>();
		int positionSource1 = 1;
		// Match titles from source1 with source 2
		// Non-matched titles will have position -1 in source2
		for (Title title : titlesSource1) {
			int positionSource2 = -1;
			try {
				positionSource2 = titlesSource2.indexOf(title);
				if (positionSource2 != -1) {
					titlesSource2.set(positionSource2, null);
					positionSource2++;
					titlesMatchedSource2.add(positionSource2);
					titlesProcessed += 1;
				}
				title.setPositionSource1(positionSource1);
				title.setPositionSource2(positionSource2);
				titles.add(title);
				positionSource1++;
				titlesProcessed += 1;
				session.setAttribute("loadPercentage", (titlesProcessed * 100) / totalTitles);
			} catch(NullPointerException e) {
				// Skip element
			}
		}
		// All matching titles from source 2 will not be processed
		// Here we will only include the position for titles unique in source 2
		// For source 1 all titles will have position -1
		int numTitlesSource2 = titlesSource2.size();
		for (int i=1; i<=numTitlesSource2; i++) {
			//if (!titlesMatchedSource2.contains(i)) {
				Title title = titlesSource2.get(i-1);
				if (title != null) {
					title.setPositionSource1(-1);
					title.setPositionSource2(i);
					titles.add(title);
					titlesProcessed += 1;
					session.setAttribute("loadPercentage", (titlesProcessed * 100) / totalTitles);
				}
			//}
		}
		System.out.println("Titles processed = " + titlesProcessed);
		
		return titles;
	}
	
	public TestResult getTestResult(Test test) throws DAOException {
		List<Par> pares = test.getPares();
		List<ParResult> paresResults = new LinkedList<ParResult>();
		Map<Integer, TopResult> mapTopResults = new LinkedHashMap<Integer, TopResult>();
		int parIndex = 0;
		for (Par par : pares) {
			System.out.println("Calculating par " + par.getId());
			List<Title> titles = par.getTitles();
			int minNumTitles = par.getTitlesSource1().size();
			//int numTitlesSource2 = par.getTitlesSource2().size();
			//int minNumTitles = (numTitlesSource1 <= numTitlesSource2) ? numTitlesSource1 : numTitlesSource2;
			Map<Integer, Double> results = new LinkedHashMap<Integer, Double>();
			List<Integer> tops = FacadeSettings.getInstancia().getTops();
			if (tops == null) {
				tops = defaultTops;
			}
			double mean = 0;
			int numTops = 0;
			for (int top : tops) {
				if (top <= minNumTitles) {
					numTops++;
					System.out.println("Calculating top " + top);
					double accumulatedDistance = 0;
					TopResult topResult = mapTopResults.get(top);
					if (topResult == null) {
						topResult = new TopResult(top);
						mapTopResults.put(top, topResult);
					}
					List<AdvancedParResult> advancedParResults = topResult.getAdvancedParResults();
					AdvancedParResult advancedParResult = null;
					if (advancedParResults.size() <= parIndex) {
						advancedParResult = new AdvancedParResult(par);
						topResult.getAdvancedParResults().add(advancedParResult);
					} else {
						advancedParResults.get(parIndex);
					}
					for (Title title : titles) {
						int posSource1 = title.getPositionSource1();
						int posSource2 = title.getPositionSource2();
						double distance = 0;
						if ((posSource1 <= top && posSource1 != -1) /*|| (posSource2 <= top && posSource2 != -1)*/) {
							if (posSource2 > top || posSource2 == -1) distance = top;
							else distance = Math.abs(posSource1 - posSource2);

							// Advanced | Same position +1							
							if (posSource2 <= top && posSource2 != -1) {
								advancedParResult.addMatching();
							}
							// Advanced | Same position +1
							if (distance == 0) {
								advancedParResult.addSamePosition();
							}
							accumulatedDistance += distance;
						}
					}
					System.out.println("Total accumulated distance: " + accumulatedDistance);
					double proximity = 100 - (100 * accumulatedDistance) / (top * top);
					/*NumberFormat nf = NumberFormat.getInstance(Locale.FRANCE);
					nf.format(proximity);*/
					results.put(top, proximity);
					advancedParResult.setProximity(proximity);
					mean += proximity;
				} else {
					// No more tops to calculate
					break;
				}
			}
			mean = mean / numTops;
			ParResult parResult = new ParResult(par, results, mean);
			paresResults.add(parResult);
			parIndex++;
		}
		List<TopResult> topsResults = new LinkedList<TopResult>(mapTopResults.values());
		TestResult testResult = new TestResult(test, paresResults, topsResults);
		
		return testResult;
	}
}