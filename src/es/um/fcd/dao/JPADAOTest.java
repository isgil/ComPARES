package es.um.fcd.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.um.fcd.model.Test;
import es.um.fcd.util.AppLogger;

public class JPADAOTest implements DAOTest {

	private EntityManagerFactory emf;

	public JPADAOTest(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public Test create(Test test) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			em.persist(test);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
			test = null;
		}
		em.close();

		return test;
	}

	@Override
	public Test find(int id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Test test = em.find(Test.class, id);
		em.close();
		
		return test;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<Test> findAll() throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT c FROM COMPARISON c";
		Query query = em.createQuery(queryString);
		List<Test> test = (List<Test>) query.getResultList();
		em.close();

		return test;
	}
	
	@Override
	public void delete(Test test) throws DAOException {
		Test c = find(test.getId());
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
