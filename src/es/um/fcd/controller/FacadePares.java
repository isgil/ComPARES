package es.um.fcd.controller;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.TestFile;

public class FacadePares extends Facade {
	private static FacadePares instancia = null;

	private FacadePares() {
	}

	public static FacadePares getInstancia() {
		if (instancia == null)
			instancia = new FacadePares();
		return instancia;
	}
	
	public Par add(TestFile testFileSource1, List<String> titlesSource1, TestFile testFileSource2, List<String> titlesSource2) throws DAOException {
		Par par = new Par(testFileSource1, titlesSource1, testFileSource2, titlesSource2);
		return getDAOFactoria().getDAOPar().create(par);
	}

	public Par get(int id) throws DAOException {
		return getDAOFactoria().getDAOPar().find(id);
	}

	public Collection<Par> getAll() throws DAOException {
		return getDAOFactoria().getDAOPar().findAll();
	}

	public void delete(Par par) throws DAOException {
		TestFile testFile1 = par.getTestFileSource1();
		TestFile testFile2 = par.getTestFileSource2();
		File file = new File(testFile1.getFullPhysicalName());
		if (file.exists()) {
			file.delete();
			file = new File(testFile2.getFullPhysicalName());
			if (file.exists()) {
				file.delete();
			}
		}
	
		getDAOFactoria().getDAOPar().delete(par);
	}

	public List<Integer> delete(Integer[] ids) throws DAOException {
		List<Integer> result = new LinkedList<Integer>();
		for (Integer id : ids) {
			try {
				Par par = get(id);
				delete(par);
			} catch (Exception e) {
				result.add(id);
			}
		}

		return result;
	}
}