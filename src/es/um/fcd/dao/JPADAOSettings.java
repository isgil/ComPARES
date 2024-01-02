package es.um.fcd.dao;

import java.util.Collection;
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

	@Override
	public Settings find(int id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Settings settings = em.find(Settings.class, id);
		em.close();
		
		return settings;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Settings> findAll() throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT s FROM SETTINGS s";
		Query query = em.createQuery(queryString);
		List<Settings> settings = (List<Settings>) query.getResultList();
		em.close();

		return settings;
	}
	
	@Override
	public void delete(Settings settings) throws DAOException {
		Settings s = find(settings.getId());
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			s = em.merge(s);
			em.remove(s);
			tx.commit();
			em.close();
		} catch (Exception e) {
			tx.rollback();
			em.close();
			throw new DAOException("Error removing settings");
		}
	};
}
