package fr.eni.clinique.dao;

public interface DAO<T> {
	void insert(T data) throws DALException;
	void update(T data) throws DALException;
	void delete(T data) throws DALException;
}
