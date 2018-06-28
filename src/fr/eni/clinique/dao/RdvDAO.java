package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Rdv;

import java.util.List;

public interface RdvDAO extends DAO<Rdv> {
    @Override
    void insert(Rdv data) throws DALException;

    @Override
    void update(Rdv data) throws DALException;

    @Override
    void delete(Rdv data) throws DALException;

    List<Rdv> getListRdvByVet(int codePers) throws DALException;
}
