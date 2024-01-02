package es.um.fcd.dao;

import java.util.Collection;

import es.um.fcd.model.Settings;

public interface DAOSettings {
	public Settings create(Settings settings) throws DAOException;

	public Settings find(int id) throws DAOException;

	public Collection<Settings> findAll() throws DAOException;
	
	public void delete(Settings settings) throws DAOException;
}