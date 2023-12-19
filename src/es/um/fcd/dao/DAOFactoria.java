package es.um.fcd.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class DAOFactoria {
	public abstract DAOComparison getDAOComparison();
	public abstract DAOPar getDAOPar();
	public abstract DAOSource getDAOSource();
	public abstract DAOUser getDAOUser();

	private static EntityManagerFactory emf;

	public final static int JPA = 1;
	public final static int MYSQL = 2;

	public static DAOFactoria getDAOFactoria(int tipo) throws DAOException {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory("SISA");
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