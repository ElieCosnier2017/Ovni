package fr.eni.clinique.dao;

import fr.eni.clinique.dao.jdbc.*;
import fr.eni.clinique.ihm.personnel.ClientDaoJdbcImpl;

public class DAOfactory {

    public static ClientDAO getClientDao() { return new ClientDaoJdbcImpl(); }

    public static ConnexionDAO getConnexionDao() { return new ConnexionDAOJdbcImpl(); }

    public static PersonnelDAO getPersonnelDao() { return new PersonnelDaoJdbcImpl();}

    public static RdvDAO getRdvDao() { return new RdvDaoJdbcImpl();}

    public static AnimalDAO getAnimalDao() { return new AnimalDaoJdbdImpl();}
}
