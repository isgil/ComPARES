package es.um.fcd.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.LinkedHashMap;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.Source;
import es.um.fcd.model.Test;
import es.um.fcd.model.TestFile;
import es.um.fcd.model.Title;
import es.um.fcd.web.model.TestResult;
import es.um.fcd.web.model.ParResult;

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

	public List<Title> getTitles(TestFile testFile) throws DAOException, FileNotFoundException, IOException {
		String extension = testFile.getExtension();
		TestFileStrategy tfs = null;
		List<Title> titles = null;
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
	
	public TestResult getTestResult(Test test) {
		List<Par> pares = test.getPares();
		Source source1 = test.getSource1();
		Source source2 = test.getSource2();
		List<ParResult> paresResults = new LinkedList<ParResult>();
		for (Par par : pares) {
			System.out.println("Calculating par " + par.getId());
			List<Title> titlesSource1 = par.getTitlesSorted(source1);
			List<Title> titlesSource2 = par.getTitlesSorted(source2);
			int minNumTitles = (titlesSource1.size() <= titlesSource2.size()) ? titlesSource1.size() : titlesSource2.size();
			Map<Integer, Integer> results = new LinkedHashMap<Integer, Integer>();
			for (int top : tops) {
				if (top <= minNumTitles) {
					System.out.println("Calculating top " + top);
					List<Title> titlesSource2forTop = titlesSource2.subList(0, top);
					int accumulatedDistance = 0;
					//generate top
					for (int t=0; t<top; t++) {
						Title titleSource1 = titlesSource1.get(t);
						int pos = titlesSource2forTop.indexOf(titleSource1);
						int distance = top;
						if (pos != -1) {
							Title titleSource2 = titlesSource2forTop.get(pos);
							distance = Math.abs(titleSource1.getPosition() - titleSource2.getPosition());
							System.out.println("Distance between " + titleSource1.getTitle() + " / " + titleSource2.getTitle() + ": " + distance);
						} else {
							System.out.println("Distance between " + titleSource1.getTitle() + " / " + titleSource1.getTitle() + ": " + distance);
						}						
						accumulatedDistance += distance;
					}
					int proximity = 100 - (100 * accumulatedDistance) / (top * top);
					results.put(top, proximity);
					System.out.println("Accumulated distance: " + accumulatedDistance);
				} else {
					// No more tops to calculate
					break;
				}
			}
			ParResult parResult = new ParResult(par, results);
			paresResults.add(parResult);
		}
		TestResult testResult = new TestResult(test, paresResults);
		
		return testResult;
	}
}