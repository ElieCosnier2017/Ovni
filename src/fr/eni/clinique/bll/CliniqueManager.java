package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dao.CliniqueDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.DAOfactory;

public class CliniqueManager {

    private CliniqueDAO daoClinique;
    private static CliniqueManager instance=null;

    private CliniqueManager(){
        //Instancier le Data Access Object
        daoClinique =DAOfactory.getCliniqueDao();
    }

    public static synchronized CliniqueManager getInstance() throws BLLException{
        if (instance == null){
            instance = new CliniqueManager();
        }
        return instance;
    }

    public void insertClient(Client c1) throws BLLException{
        if(c1.getCodeClient() != null){
            throw new BLLException("Article deja existant.");
        }
        try{
            daoClinique.insert(c1);
        }catch (DALException e){
            throw new BLLException("Echec insertion client - ",e);
        }
    }
}
