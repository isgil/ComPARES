package es.um.fcd.dao;

import java.util.Collection;

import es.um.fcd.model.User;

public interface DAOUser {
	public User create(String username, String password, String email, int group) throws DAOException;

	public User find(String username) throws DAOException;

	public Collection<User> findAll() throws DAOException;

	public void delete(String userName) throws DAOException;
}