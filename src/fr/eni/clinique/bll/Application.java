package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Client;

public class Application {

    public static void main(String[] args){

        LoginMger mger = null;
        try {
            mger = LoginMger.getInstance();
        } catch (BLLException e1) {
            e1.printStackTrace();;
        }

        ClientManager cliniqueMger = null;
        try {
            cliniqueMger = ClientManager.getInstance();
        } catch (BLLException e1) {
            e1.printStackTrace();
        }

        Client c1 = new Client("Gonzales", "Pedro","10 rue de la banane", "11 rue de la frite", "35000",
                "Paris", "0123456789", "Oui","jean@lafritte.com","Trop Cute", true );

        try {
            cliniqueMger.insertClient(c1);
        } catch (BLLException e) {
            e.printStackTrace();
        }

        String username = "Juan";
        String password = "yolo";

        mger.verifyUser(username,password);
    }
}
