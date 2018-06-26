package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Personnel;

public interface ConnexionDAO {
    Personnel verifyUser(String username, String password) throws DALException;
}
