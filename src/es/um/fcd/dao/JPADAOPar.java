package es.um.fcd.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.um.fcd.model.Par;
import es.um.fcd.util.AppLogger;

public class JPADAOPar implements DAOPar {

	private EntityManagerFactory emf;

	public JPADAOPar(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Par create(Par par) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			em.persist(par);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
			par = null;
		}
		em.close();

		return par;
	}

	@Override
	public Par find(int id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Par par = em.find(Par.class, id);
		em.close();
		
		return par;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Par> findAll() throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT p FROM PAR p";
		Query query = em.createQuery(queryString);
		List<Par> pares = (List<Par>) query.getResultList();
		em.close();

		return pares;
	}
	
	@Override
	public void update(Par par) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			par = em.merge(par);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
		}
		em.close();
	}
	
	@Override
	public void delete(Par par) throws DAOException {
		Par p = find(par.getId());
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			p = em.merge(p);
			em.remove(p);
			tx.commit();
			em.close();
		} catch (Exception e) {
			tx.rollback();
			em.close();
			throw new DAOException("Error removing par");
		}		
	};
}
