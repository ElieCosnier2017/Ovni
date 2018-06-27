package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dao.ClientDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.DAOfactory;

public class ClientManager {

    private ClientDAO daoClient;
    private static ClientManager instance=null;

    private ClientManager(){
        //Instancier le Data Access Object
        daoClient =DAOfactory.getClientDao();
    }

    public static synchronized ClientManager getInstance() throws BLLException{
        if (instance == null){
            instance = new ClientManager();
        }
        return instance;
    }

    public void insertClient(Client c1) throws BLLException{
        if(c1.getCodeClient() != null){
            throw new BLLException("Client deja existant.");
        }
        try{
            daoClient.insert(c1);
        }catch (DALException e){
            throw new BLLException("Echec insertion client - ",e);
        }
    }
}
