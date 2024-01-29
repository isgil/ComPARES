package es.um.fcd.controller;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Source;
import es.um.fcd.model.Title;

public class FacadeTitles extends Facade {
	private static FacadeTitles instancia = null;

	private FacadeTitles() {
	}

	public static FacadeTitles getInstancia() {
		if (instancia == null)
			instancia = new FacadeTitles();
		return instancia;
	}
	
	public Title add(String titleStr, int position, Source source) throws DAOException {		
		Title title = new Title(titleStr, position, source);
		return getDAOFactoria().getDAOTitle().create(title);
	}

	public Title get(int id) throws DAOException {
		return getDAOFactoria().getDAOTitle().find(id);
	}

	public Collection<Title> getAll() throws DAOException {
		return getDAOFactoria().getDAOTitle().findAll();
	}

	public void delete(Title title) throws DAOException {
		getDAOFactoria().getDAOTitle().delete(title);
	}

	public List<Integer> delete(Integer[] ids) throws DAOException {
		List<Integer> result = new LinkedList<Integer>();
		for (Integer id : ids) {
			try {
				Title title = get(id);
				delete(title);
			} catch (Exception e) {
				result.add(id);
			}
		}

		return result;
	}
}