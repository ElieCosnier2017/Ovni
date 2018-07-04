package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dao.ClientDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.DAOfactory;

import java.util.ArrayList;
import java.util.List;

public class ClientManager {

    private ClientDAO daoClient;
    private static ClientManager instance=null;

    private ClientManager(){
        //Instancier le Data Access Object
        daoClient =DAOfactory.getClientDao();
    }

    public static synchronized ClientManager getInstance(){
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

    public void updateClient(Client c1) throws BLLException{
        try{
            daoClient.update(c1);
        }catch (DALException e){
            throw new BLLException("Echec mise à jour client - ",e);
        }
    }

    public void deleteClient(Client c1) throws BLLException{
        try{
            daoClient.delete(c1);
        }catch (DALException e){
            throw new BLLException("Echec supression client - ",e);
        }
    }

    public Client findClientByName(String nomClient) throws BLLException{
        try{
            Client client = daoClient.selectOneByName(nomClient);
            return client;
        }catch (DALException e){
            throw new BLLException("Echec récupération client - ",e);
        }
    }

    public List<Client> getAllClients() throws BLLException{
        try{
            List<Client> clientList = new ArrayList<>();
            clientList = daoClient.selectAll();
            return clientList;
        }catch (DALException e){
            throw new BLLException("Echec récupération tous les clients - ",e);
        }
    }

    public List<Client> findClientsByName(String name) throws BLLException{
        try {
            List<Client> clientList = new ArrayList<>();
            clientList = daoClient.findByName(name);
            return clientList;
        } catch (DALException e) {
            throw new BLLException("Echec récupération tous les clients - ",e);
        }
    }
}
