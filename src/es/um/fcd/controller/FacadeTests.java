package es.um.fcd.controller;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.Source;
import es.um.fcd.model.Test;
import es.um.fcd.model.TestFile;
import es.um.fcd.model.Title;

public class FacadeTests extends Facade {
	private static FacadeTests instancia = null;

	private FacadeTests() {
	}

	public static FacadeTests getInstancia() {
		if (instancia == null)
			instancia = new FacadeTests();
		return instancia;
	}
	
	public Test add(String testName, Source source1, Source source2, String titleMark1, String titleMark2, List<Par> pares) throws DAOException {		
		Test test = new Test(testName, source1, source2, titleMark1, titleMark2, pares);
		/*
		List<Title> titles = test.getPares().get(0).getTitlesSource1();
		for (Title title : titles) {
			System.out.println(title.getTitle());
		}
		titles = test.getPares().get(0).getTitlesSource2();
		for (Title title : titles) {
			System.out.println(title.getTitle());
		}
		*/
		return getDAOFactoria().getDAOTest().create(test);
	}

	public Test get(int id) throws DAOException {
		return getDAOFactoria().getDAOTest().find(id);
	}

	public Collection<Test> getAll() throws DAOException {
		return getDAOFactoria().getDAOTest().findAll();
	}

	public void delete(Test test) throws DAOException {
		for (Par par : test.getPares()) {
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
		}
		getDAOFactoria().getDAOTest().delete(test);
	}

	public List<Integer> delete(Integer[] ids) throws DAOException {
		List<Integer> result = new LinkedList<Integer>();
		for (Integer id : ids) {
			try {
				Test test = get(id);
				delete(test);
			} catch (Exception e) {
				result.add(id);
			}
		}

		return result;
	}
}