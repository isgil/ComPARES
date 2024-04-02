package es.um.fcd.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.um.fcd.model.Settings;
import es.um.fcd.util.AppLogger;

public class JPADAOSettings implements DAOSettings {

	private EntityManagerFactory emf;

	public JPADAOSettings(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Settings create(Settings settings) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			em.persist(settings);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
			settings = null;
		}
		em.close();

		return settings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Settings getAll() throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT s FROM SETTINGS s";
		Query query = em.createQuery(queryString);
		List<Settings> result = (List<Settings>) query.getResultList();
		em.close();

		if (result.isEmpty())
			return null;
		return result.get(0);
	}
	
	@Override
	public Settings update(Settings settings) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			em.merge(settings);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
		}
		em.close();
		
		return settings;
	}
}
