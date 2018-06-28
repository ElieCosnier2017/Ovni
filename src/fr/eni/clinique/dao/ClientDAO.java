package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Client;

import java.util.List;

public interface ClientDAO {
    void insert(Client c1) throws DALException;
    void update(Client c1) throws DALException;
    void delete(Client c1) throws DALException;
    List<Client> selectAll() throws DALException;
    Client selectOne(Integer CodeClient) throws DALException;
}
