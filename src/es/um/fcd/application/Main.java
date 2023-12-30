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
			
			// Par
			Par par = new Par("export Source 1.txt", source1, "title", "export Source 2.txt", source2, "ti");
			
			// Test
			List<Par> pares = new LinkedList<Par>();
			pares.add(par);
			Test test = new Test("Test1", pares);
			
			daoSource.create(source1);
			daoSource.create(source2);
			daoPar.create(par);
			daoTest.create(test);
		} catch (DAOException e) {
			AppLogger.log("DAO Error");
			e.printStackTrace();
		}
	}

}
