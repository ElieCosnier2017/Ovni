package fr.eni.clinique.dao;

import fr.eni.clinique.dao.jdbc.ClientDaoJdbcImpl;
import fr.eni.clinique.dao.jdbc.ConnexionDAOJdbcImpl;
import fr.eni.clinique.dao.jdbc.PersonnelDaoJdbcImpl;
import fr.eni.clinique.dao.jdbc.RdvDaoJdbcImpl;

public class DAOfactory {

    public static ClientDAO getClientDao() { return new ClientDaoJdbcImpl(); }

    public static ConnexionDAO getConnexionDao() { return new ConnexionDAOJdbcImpl(); }

    public static PersonnelDAO getPersonnelDao() { return new PersonnelDaoJdbcImpl();}

    public static RdvDAO getRdvDao() { return new RdvDaoJdbcImpl();}
}
