package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dao.ConnexionDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.DAOfactory;

public class LoginMger {

    private ConnexionDAO connexionDAO;
    private static LoginMger instance=null;
    private LoginMger(){
        connexionDAO = DAOfactory.getConnexionDao();
    }

    public static synchronized LoginMger getInstance() throws BLLException{
        if (instance == null){
            instance = new LoginMger();
        }
        return instance;
    }

    public Boolean verifyUser(String username, String mdp){
        Boolean cnxValid = false;
        try {
            Personnel user = connexionDAO.verifyUser(username,mdp);
            if(user != null){
                cnxValid = true;
            }
        } catch (DALException e) {
            e.printStackTrace();
        }
        return cnxValid;
    }
}
