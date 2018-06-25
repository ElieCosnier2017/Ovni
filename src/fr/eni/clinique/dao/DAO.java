package fr.eni.clinique.dao;

public interface DAO<T> {
	void create(T data);
	T read(int id);
	void update(T data);
	void delete(T data);
}
