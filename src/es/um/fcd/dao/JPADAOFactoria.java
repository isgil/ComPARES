package es.um.fcd.dao;

import javax.persistence.EntityManagerFactory;

public class JPADAOFactoria extends DAOFactory {

	private EntityManagerFactory emf;

	public JPADAOFactoria(EntityManagerFactory emf) throws DAOException {
		this.emf = emf;
	}
	
	@Override
	public DAOTest getDAOTest() {
		return new JPADAOTest(this.emf);
	}

	@Override
	public DAOPar getDAOPar() {
		return new JPADAOPar(this.emf);
	}

	@Override
	public DAOSource getDAOSource() {
		return new JPADAOSource(this.emf);
	}
	
	@Override
	public DAOSettings getDAOSettings() {
		return new JPADAOSettings(this.emf);
	}
	
	@Override
	public DAOUser getDAOUser() {
		return new JPADAOUser(this.emf);
	}
}
