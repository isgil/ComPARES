package es.um.fcd.controller;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Par;
import es.um.fcd.model.Source;

public class FacadePares extends Facade {
	private static FacadePares instancia = null;

	private FacadePares() {
	}

	public static FacadePares getInstancia() {
		if (instancia == null)
			instancia = new FacadePares();
		return instancia;
	}
	
	public Par add(String fileName1, Source source1, String titleMark1, String fileName2, Source source2, String titleMark2) throws DAOException {
		Par par = new Par(fileName1, source1, titleMark1, fileName2, source2, titleMark2);
		return getDAOFactoria().getDAOPar().create(par);
	}

	public Par get(int id) throws DAOException {
		return getDAOFactoria().getDAOPar().find(id);
	}

	public Collection<Par> getAll() throws DAOException {
		return getDAOFactoria().getDAOPar().findAll();
	}

	public void delete(Par par) throws DAOException {
		String filePath1 = par.getFileName1();
		String filePath2 = par.getFileName2();
		File file = new File(filePath1);
		if (file.exists()) {
			file.delete();
			file = new File(filePath2);
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