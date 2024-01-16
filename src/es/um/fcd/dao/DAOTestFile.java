package es.um.fcd.dao;

import java.util.Collection;

import es.um.fcd.model.TestFile;

public interface DAOTestFile {
	public TestFile create(TestFile testFile) throws DAOException;

	public TestFile find(int id) throws DAOException;

	public Collection<TestFile> findAll() throws DAOException;
	
	public void delete(TestFile test) throws DAOException;
}