package es.um.fcd.dao;

import java.util.Collection;

import es.um.fcd.model.Par;

public interface DAOPar {
	public Par create(Par par) throws DAOException;

	public Par find(int id) throws DAOException;

	public Collection<Par> findAll() throws DAOException;
	
	public void update(Par par) throws DAOException;
	
	public void delete(Par par) throws DAOException;
}