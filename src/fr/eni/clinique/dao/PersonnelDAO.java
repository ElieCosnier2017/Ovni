package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Personnel;

import java.util.List;

public interface PersonnelDAO {
    void insert(Personnel p1) throws DALException;
    void update(Personnel p1) throws DALException;
    void delete(Personnel p1) throws DALException;
    List<Personnel> selectAll() throws DALException;
    Personnel selectOne(int CodePers) throws DALException;

}