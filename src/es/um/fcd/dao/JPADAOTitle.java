package es.um.fcd.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.um.fcd.model.Title;
import es.um.fcd.util.AppLogger;

public class JPADAOTitle implements DAOTitle {

	private EntityManagerFactory emf;

	public JPADAOTitle(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Title create(Title title) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			em.persist(title);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
			title = null;
		}
		em.close();

		return title;
	}

	@Override
	public Title find(int id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Title title = em.find(Title.class, id);
		em.close();
		
		return title;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Title> findAll() throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT t FROM TITLE t";
		Query query = em.createQuery(queryString);
		List<Title> title = (List<Title>) query.getResultList();
		em.close();

		return title;
	}
	
	@Override
	public void delete(Title title) throws DAOException {
		Title t = find(title.getId());
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			t = em.merge(t);
			em.remove(t);
			tx.commit();
			em.close();
		} catch (Exception e) {
			tx.rollback();
			em.close();
			throw new DAOException("Error removing titles");
		}
	};
}
