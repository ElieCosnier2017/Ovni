package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Client;

import java.sql.SQLException;

public interface CliniqueDAO {
    void insert(Client c1) throws DALException;
}
