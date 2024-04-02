package es.um.fcd.dao;

import es.um.fcd.model.Settings;

public interface DAOSettings {
	public Settings create(Settings settings) throws DAOException;

	public Settings getAll() throws DAOException;

	Settings update(Settings settings) throws DAOException;
}