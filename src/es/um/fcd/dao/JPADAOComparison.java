package es.um.fcd.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.um.fcd.model.Comparison;
import es.um.fcd.model.Par;
import es.um.fcd.model.Source;
import es.um.tfg.atfc.db.modelo.Idioma;
import es.um.tfg.atfc.sisa.util.AppLogger;

public class JPADAOComparison implements DAOComparison {

	private EntityManagerFactory emf;

	public JPADAOComparison(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Comparison create() throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		Comparison comparison = new Comparison();
		tx.begin();

		try {
			em.persist(comparison);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
			comparison = null;
		}
		em.close();

		return comparison;
	}

	@Override
	public Comparison find(int id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Comparison comparison = em.find(Comparison.class, id);
		em.close();
		
		return comparison;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Comparison> findAll() throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT c FROM COMPARISON c";
		Query query = em.createQuery(queryString);
		List<Comparison> comparison = (List<Comparison>) query.getResultList();
		em.close();

		return comparison;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void delete(Comparison comparison) throws DAOException {
		Comparison c = find(comparison.getId());
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			c = em.merge(c);
			em.remove(c);
			tx.commit();
			em.close();
		} catch (Exception e) {
			tx.rollback();
			em.close();
			throw new DAOException("Error removing comparison");
		}		
	};
}
