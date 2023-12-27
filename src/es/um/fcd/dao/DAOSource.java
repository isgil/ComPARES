package es.um.fcd.dao;

import java.util.Collection;

import es.um.fcd.model.Source;

public interface DAOSource {
	public Source create(Source source) throws DAOException;

	public Source find(String source) throws DAOException;
	
	public Source find(int id) throws DAOException;

	public Collection<Source> findAll() throws DAOException;

	public void delete(Source source) throws DAOException;
}