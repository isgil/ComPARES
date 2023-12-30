package es.um.fcd.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Source;

public class FacadeSources extends Facade {
	private static FacadeSources instancia = null;

	private FacadeSources() {
	}

	public static FacadeSources getInstancia() {
		if (instancia == null)
			instancia = new FacadeSources();
		return instancia;
	}
	
	public Source add(String sourceName) throws DAOException {
		Source source = new Source(sourceName);
		return getDAOFactoria().getDAOSource().create(source);
	}

	public Source get(String source) throws DAOException {
		return getDAOFactoria().getDAOSource().find(source);
	}
	
	public Source get(int id) throws DAOException {
		return getDAOFactoria().getDAOSource().find(id);
	}
	
	public boolean exists(String sourceName) throws DAOException {
		Source source = get(sourceName);
		
		return (source != null);
	}

	public Collection<Source> getAll() throws DAOException {
		return getDAOFactoria().getDAOSource().findAll();
	}

	public void delete(Source source) throws DAOException {	
		getDAOFactoria().getDAOSource().delete(source);
	}

	public List<Integer> delete(Integer[] ids) throws DAOException {
		List<Integer> result = new LinkedList<Integer>();
		for (Integer id : ids) {
			try {
				Source source = get(id);
				delete(source);
			} catch (Exception e) {
				result.add(id);
			}
		}

		return result;
	}
}