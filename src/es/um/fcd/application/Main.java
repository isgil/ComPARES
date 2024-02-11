package es.um.fcd.application;

import java.util.LinkedList;
import java.util.List;

import es.um.fcd.controller.FacadePares;
import es.um.fcd.controller.FacadeTests;
import es.um.fcd.dao.DAOException;
import es.um.fcd.dao.DAOFactory;
import es.um.fcd.dao.DAOPar;
import es.um.fcd.dao.DAOSource;
import es.um.fcd.dao.DAOTest;
import es.um.fcd.model.Par;
import es.um.fcd.model.Source;
import es.um.fcd.model.Test;
import es.um.fcd.model.TestFile;
import es.um.fcd.model.Title;
import es.um.fcd.util.AppLogger;

public class Main {

	public static void main(String[] args) {
		/*Title title1 = new Title("Region de Murcia", 1);
		Title title2 = new Title("Región de murcia", 1);
		System.out.println(title1.equals(title2));
		*/
		
		DAOFactory daoFactoria;
		try {
			daoFactoria = DAOFactory.getDAOFactoria(DAOFactory.JPA);
			DAOSource daoSource = daoFactoria.getDAOSource();
			//DAOPar daoPar = daoFactoria.getDAOPar();
			DAOTest daoTest = daoFactoria.getDAOTest();
			
			
			// Sources
			Source source1 = new Source("Redalyc");
			Source source2 = new Source("UMU");
			Source source3 = new Source("Revista3");
			Source source4 = new Source("Revista4");
			
			// TestFiles
			TestFile testFile1 = new TestFile("export Source 1.txt", "path");
			TestFile testFile2 = new TestFile("export Source 2.txt", "path");
			TestFile testFile3 = new TestFile("export Source 3.txt", "path");
			TestFile testFile4 = new TestFile("export Source 4.txt", "path");
			TestFile testFile5 = new TestFile("export Source 5.txt", "path");
			TestFile testFile6 = new TestFile("export Source 6.txt", "path");
			
			// TestFile titles
			List<Title> titles = new LinkedList<Title>();
			Title title1 = new Title("title11", 0, source1);
			Title title2 = new Title("title12", 1, source1);
			Title title3 = new Title("title13", 2, source1);
			Title title4 = new Title("title21", 0, source2);
			Title title5 = new Title("title22", 1, source2);
			Title title6 = new Title("title23", 2, source2);
			titles.add(title1);
			titles.add(title2);
			titles.add(title3);
			titles.add(title4);
			titles.add(title5);
			titles.add(title6);
			
			// Par
			Par par1 = new Par(testFile1, testFile2, titles);
			//Par par2 = new Par(testFile3, testFile4, titles);
			//Par par3 = new Par(testFile5, testFile6, titles);

			// Source
			source1 = daoSource.create(source1);
			source2 = daoSource.create(source2);
			
			// Test
			List<Par> pares1 = new LinkedList<Par>();
			pares1.add(par1);
			//pares1.add(par2);
			Test test1 = new Test("Test1", source1, source2, "title1", "title2", pares1);
						
			List<Par> pares2 = new LinkedList<Par>();
			//pares2.add(par3);
			Test test2 = new Test("Test2", source1, source2, "ti1", "ti2", pares2);
			
			
			
			//daoSource.create(source3);
			//daoSource.create(source4);
			//daoPar.create(par1);
			//daoPar.create(par2);
			//daoPar.create(par3);
			
			test1 = daoTest.create(test1);
			System.out.println("T1 ID" + test1.getId());
			System.out.println("T1 S1 ID" + test1.getSource1().getId());
			System.out.println("T1 S2 ID" + test1.getSource2().getId());
			System.out.println("T1 P1 ID" + test1.getPares().get(0).getId());
			//System.out.println("T1 P2 ID" + test1.getPares().get(1).getId());
			System.out.println("T1 P1 TF1 ID" + test1.getPares().get(0).getTestFileSource1().getId());
			//System.out.println(test2.getId());
			
			//daoTest.create(test2);
			//System.out.println("T2 ID" + test2.getId());
			//System.out.println("T2 S1 ID" + test2.getSource1().getId());
			//System.out.println("T2 S2 ID" + test2.getSource2().getId());
			//System.out.println("T2 P1 ID" + test2.getPares().get(0).getId());
			//System.out.println("T2 P1 TF1 ID" + test2.getPares().get(0).getTestFileSource1().getId());
			
		} catch (DAOException e) {
			AppLogger.log("DAO Error");
			e.printStackTrace();
		}
		
	}

}
