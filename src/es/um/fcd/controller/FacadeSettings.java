package es.um.fcd.controller;

import java.util.List;
import java.util.Map;

import es.um.fcd.dao.DAOException;
import es.um.fcd.model.Settings;

public class FacadeSettings extends Facade {
	private static FacadeSettings instancia = null;

	private FacadeSettings() {
	}

	public static FacadeSettings getInstancia() {
		if (instancia == null)
			instancia = new FacadeSettings();
		return instancia;
	}
	
	public Settings add(Settings settings) throws DAOException {
		return getDAOFactoria().getDAOSettings().create(settings);
	}
	
	public Settings getAll() throws DAOException {
		return getDAOFactoria().getDAOSettings().getAll();
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getTops() throws DAOException {
		Settings dbSettings = getAll();
		if (dbSettings != null) {
			Map<String, Object> settings = dbSettings.getSettings();
			if (settings != null) {
				List<Integer> tops = (List<Integer>)settings.get("tops");
				return tops;
			}
		}
		
		return null;
	}
	
	public Settings update(Settings settings) throws DAOException {
		return getDAOFactoria().getDAOSettings().update(settings);
	}
}