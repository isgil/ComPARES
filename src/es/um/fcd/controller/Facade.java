package es.um.fcd.controller;

import es.um.fcd.dao.DAOException;
import es.um.fcd.dao.DAOFactory;
import es.um.fcd.util.AppLogger;

public abstract class Facade {
	protected static DAOFactory DAO_FACTORY = null;

	protected static DAOFactory getDAOFactoria() {
		try {
			if (DAO_FACTORY == null) {
				DAO_FACTORY = DAOFactory.getDAOFactoria(DAOFactory.JPA);
			}
		} catch (DAOException e) {
			AppLogger.logException(e);
		}

		return DAO_FACTORY;
	}
}
