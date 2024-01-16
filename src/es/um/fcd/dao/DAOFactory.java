package es.um.fcd.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOFactory {
	public abstract DAOTest getDAOTest();
	public abstract DAOTestFile getDAOTestFile();
	public abstract DAOPar getDAOPar();
	public abstract DAOSource getDAOSource();
	public abstract DAOSettings getDAOSettings();
	public abstract DAOUser getDAOUser();

	private static EntityManagerFactory emf;

	public final static int JPA = 1;
	public final static int MYSQL = 2;

	public static DAOFactory getDAOFactoria(int tipo) throws DAOException {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("COMPARES");
		}
		switch (tipo) {
		case JPA:
			try {
				return new JPADAOFactoria(emf);
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		default:
			return null;
		}
	}
}