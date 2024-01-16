package es.um.fcd.controller;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.TestFile;

public class FacadeTestFiles extends Facade {
	private static FacadeTestFiles instancia = null;

	private FacadeTestFiles() {
	}

	public static FacadeTestFiles getInstancia() {
		if (instancia == null)
			instancia = new FacadeTestFiles();
		return instancia;
	}
	
	public TestFile add(String fileName, String filePath) throws DAOException {
		TestFile testFile = new TestFile(fileName, filePath);
		return getDAOFactoria().getDAOTestFile().create(testFile);
	}

	public TestFile get(int id) throws DAOException {
		return getDAOFactoria().getDAOTestFile().find(id);
	}

	public Collection<TestFile> getAll() throws DAOException {
		return getDAOFactoria().getDAOTestFile().findAll();
	}

	public void delete(TestFile testFile) throws DAOException {
		File file = new File(testFile.getFullPhysicalName());
		if (file.exists()) {
			file.delete();
		}
	}

	public List<Integer> delete(Integer[] ids) throws DAOException {
		List<Integer> result = new LinkedList<Integer>();
		for (Integer id : ids) {
			try {
				TestFile testFile = get(id);
				delete(testFile);
			} catch (Exception e) {
				result.add(id);
			}
		}

		return result;
	}
}