package es.um.fcd.dao;

import java.util.Collection;

import es.um.fcd.model.Comparison;

public interface DAOComparison {
	public Comparison create() throws DAOException;

	public Comparison find(int id) throws DAOException;

	public Collection<Comparison> findAll() throws DAOException;
	
	public void delete(Comparison comparison) throws DAOException;
}