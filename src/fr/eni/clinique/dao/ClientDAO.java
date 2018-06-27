package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Client;

public interface ClientDAO {
    void insert(Client c1) throws DALException;
    void update(Client c1) throws DALException;
    void delete(Client c1) throws DALException;
}
