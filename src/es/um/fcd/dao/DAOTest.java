package es.um.fcd.dao;

import java.util.Collection;

import es.um.fcd.model.Test;

public interface DAOTest {
	public Test create(Test test) throws DAOException;

	public Test find(int id) throws DAOException;

	public Collection<Test> findAll() throws DAOException;
	
	public void delete(Test test) throws DAOException;
}