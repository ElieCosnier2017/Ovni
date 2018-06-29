package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Animal;

public interface AnimalDAO extends  DAO<Animal>{
        void insert(Animal a1) throws DALException;
        void update(Animal a1) throws DALException;
        void delete(Animal a1) throws DALException;
}
