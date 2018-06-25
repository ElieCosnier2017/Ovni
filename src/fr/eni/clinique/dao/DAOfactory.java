package fr.eni.clinique.dao;

import fr.eni.clinique.dao.jdbc.CliniqueDaoJdbcImpl;

public class DAOfactory {

    public static CliniqueDAO getCliniqueDao() {
        return new CliniqueDaoJdbcImpl();
    }
}
