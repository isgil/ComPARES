package es.um.fcd.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.um.fcd.model.Source;
import es.um.fcd.util.AppLogger;

public class JPADAOSource implements DAOSource {

	private EntityManagerFactory emf;

	public JPADAOSource(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Source create(Source source) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			em.persist(source);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
			source = null;
		}
		em.close();

		return source;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Source find(String source) throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT t FROM SOURCE t " + "WHERE t.name = :name";
		Query query = em.createQuery(queryString);
		query.setParameter("name", source);
		List<Source> result = (List<Source>) query.getResultList();
		em.close();

		if (result.isEmpty())
			return null;
		return result.get(0);
	}
	
	@Override
	public Source find(int id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Source source = em.find(Source.class, id);
		em.close();
		
		return source;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Source> findAll() throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT t FROM SOURCE t";
		Query query = em.createQuery(queryString);
		List<Source> sources = (List<Source>) query.getResultList();
		em.close();

		return sources;
	}

	@Override
	public void delete(Source source) throws DAOException {
		Source s = find(source.getName());
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
			throw new DAOException("Error removing source");
		}
	}
}
