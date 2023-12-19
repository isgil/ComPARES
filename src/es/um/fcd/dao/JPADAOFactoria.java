package es.um.fcd.dao;

import javax.persistence.EntityManagerFactory;

public class JPADAOFactoria extends DAOFactoria {

	private EntityManagerFactory emf;

	public JPADAOFactoria(EntityManagerFactory emf) throws DAOException {
		this.emf = emf;
	}

	@Override
	public DAOUser getDAOUser() {
		return new JPADAOUser(this.emf);
	}

	@Override
	public DAOPar getDAOPar() {
		return new JPADAOPar(this.emf);
	}

	@Override
	public DAOSource getDAOSource() {
		return new JPADAOSource(this.emf);
	}
}
