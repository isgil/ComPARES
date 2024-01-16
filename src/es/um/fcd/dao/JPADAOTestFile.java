package es.um.fcd.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.um.fcd.model.TestFile;
import es.um.fcd.util.AppLogger;

public class JPADAOTestFile implements DAOTestFile {

	private EntityManagerFactory emf;

	public JPADAOTestFile(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public TestFile create(TestFile testFile) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			em.persist(testFile);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
			testFile = null;
		}
		em.close();

		return testFile;
	}

	@Override
	public TestFile find(int id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		TestFile test = em.find(TestFile.class, id);
		em.close();
		
		return test;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<TestFile> findAll() throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT tf FROM TEST_FILE tf";
		Query query = em.createQuery(queryString);
		List<TestFile> test = (List<TestFile>) query.getResultList();
		em.close();

		return test;
	}
	
	@Override
	public void delete(TestFile test) throws DAOException {
		TestFile tf = find(test.getId());
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			tf = em.merge(tf);
			em.remove(tf);
			tx.commit();
			em.close();
		} catch (Exception e) {
			tx.rollback();
			em.close();
			throw new DAOException("Error removing test");
		}
	};
}
