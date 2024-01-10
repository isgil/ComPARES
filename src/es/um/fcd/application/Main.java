package es.um.fcd.application;

import java.util.LinkedList;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.dao.DAOFactory;
import es.um.fcd.dao.DAOPar;
import es.um.fcd.dao.DAOSource;
import es.um.fcd.dao.DAOTest;
import es.um.fcd.model.Par;
import es.um.fcd.model.Source;
import es.um.fcd.model.Test;
import es.um.fcd.util.AppLogger;

public class Main {

	public static void main(String[] args) {
		DAOFactory daoFactoria;
		try {
			daoFactoria = DAOFactory.getDAOFactoria(DAOFactory.JPA);
			DAOSource daoSource = daoFactoria.getDAOSource();
			DAOPar daoPar = daoFactoria.getDAOPar();
			DAOTest daoTest = daoFactoria.getDAOTest();
			
			// Sources
			Source source1 = new Source("Redalyc");
			Source source2 = new Source("UMU");
			Source source3 = new Source("Revista3");
			Source source4 = new Source("Revista4");
			
			// Par
			Par par1 = new Par("export Source 1.txt", source1, "title", "export Source 2.txt", source2, "ti");
			Par par2 = new Par("export Source 3.txt", source3, "tit", "export Source 4.txt", source4, "titulo");
			Par par3 = new Par("export Source 5.txt", source3, "tit", "export Source 6.txt", source4, "titulo");
			
			// Test
			List<Par> pares1 = new LinkedList<Par>();
			pares1.add(par1);
			pares1.add(par2);
			Test test1 = new Test("Test1", pares1);
						
			List<Par> pares2 = new LinkedList<Par>();
			pares2.add(par3);
			Test test2 = new Test("Test2", pares2);
			
			daoSource.create(source1);
			daoSource.create(source2);			
			daoSource.create(source3);
			daoSource.create(source4);
			daoPar.create(par1);
			daoPar.create(par2);
			daoPar.create(par3);
			daoTest.create(test1);
			daoTest.create(test2);
		} catch (DAOException e) {
			AppLogger.log("DAO Error");
			e.printStackTrace();
		}
	}

}
