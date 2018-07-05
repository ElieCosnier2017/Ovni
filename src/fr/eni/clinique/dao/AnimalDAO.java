package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Animal;

import java.util.List;

public interface AnimalDAO extends  DAO<Animal>{
    List<Animal> selectAll() throws DALException;
    List<Animal> selectAllByClient(int codeClient) throws DALException;
    Animal selectOne(int codeAnimal) throws DALException;
    void insert(Animal a1) throws DALException;
    void update(Animal a1) throws DALException;
    void delete(Animal a1) throws DALException;
}
