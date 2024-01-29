package es.um.fcd.dao;

import java.util.Collection;

import es.um.fcd.model.Title;

public interface DAOTitle {
	public Title create(Title title) throws DAOException;

	public Title find(int id) throws DAOException;

	public Collection<Title> findAll() throws DAOException;
	
	public void delete(Title title) throws DAOException;
}