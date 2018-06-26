package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dao.ConnexionDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.DAOfactory;
import sun.print.PeekGraphics;

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

    public void verifyUser(String username, String mdp){
        try {
            Personnel user = connexionDAO.verifyUser(username,mdp);
            if(user != null){
                System.out.println("User OK");
            }
        } catch (DALException e) {
            e.printStackTrace();
        }
    }
}
