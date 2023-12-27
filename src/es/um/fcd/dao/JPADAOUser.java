package es.um.fcd.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import es.um.fcd.model.User;
import es.um.fcd.util.AppLogger;

public class JPADAOUser implements DAOUser {

	private EntityManagerFactory emf;

	public JPADAOUser(EntityManagerFactory emf) {
		this.emf = emf;
	}

	@Override
	public User create(String username, String password, String email, int group) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setGroup(group);

		tx.begin();
		try {
			em.persist(user);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
			user = null;
		}
		em.close();

		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User find(String username) throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT u FROM USUARIO u " + "WHERE u.nombre = :nombre";
		Query query = em.createQuery(queryString);
		query.setParameter("nombre", username);
		List<User> result = (List<User>) query.getResultList();
		em.close();
		
		if (result.isEmpty())
			return null;
		return result.get(0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Collection<User> findAll() throws DAOException {
		EntityManager em = emf.createEntityManager();
		
		String queryString = "SELECT u FROM USUARIO u";
		Query query = em.createQuery(queryString);
		List<User> users = (List<User>) query.getResultList();
		em.close();

		return users;
	}

	@Override
	public void update(User user) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			user = em.merge(user);
			tx.commit();
		} catch (Exception e) {
			AppLogger.logException(e);
			tx.rollback();
		}
		em.close();
	}

	@Override
	public void delete(User user) throws DAOException {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		tx.begin();
		try {
			user = em.merge(user);
			em.remove(user);
			tx.commit();
			em.close();
		} catch (Exception e) {
			tx.rollback();
			em.close();
			throw new DAOException("Error removing user");
		}
	}

}
