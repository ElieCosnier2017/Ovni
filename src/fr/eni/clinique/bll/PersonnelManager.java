package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.DAOfactory;
import fr.eni.clinique.dao.PersonnelDAO;

public class PersonnelManager {
    private PersonnelDAO personnelDAO;
    private static PersonnelManager instance=null;

    private PersonnelManager(){
        //Instancier le Data Access Object
        personnelDAO =DAOfactory.getPersonnelDao();
    }

    public static synchronized PersonnelManager getInstance(){
        if (instance == null){
            instance = new PersonnelManager();
        }
        return instance;
    }


    public void insertPers(Personnel p1) throws BLLException {
        if(p1.getId() != null){
            throw new BLLException("Personnel deja existant.");
        }
        try{
            personnelDAO.insert(p1);
        }catch (DALException e){
            throw new BLLException("Echec insertion pers - ",e);
        }
    }
}
