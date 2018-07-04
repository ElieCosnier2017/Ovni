package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Client;

import java.util.List;

public interface ClientDAO extends DAO<Client>{
    void insert(Client c1) throws DALException;
    void update(Client c1) throws DALException;
    void delete(Client c1) throws DALException;
    List<Client> selectAll() throws DALException;
    Client selectOne(Integer codeClient) throws DALException;
    Client selectOneByName(String nomClient) throws DALException;
    List<Client> findByName(String name) throws DALException;

}
