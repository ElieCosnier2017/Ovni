package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;

public class Application {

    public static void main(String[] args){

        LoginMger mger = null;
        try {
            mger = LoginMger.getInstance();
        } catch (BLLException e1) {
            e1.printStackTrace();
        }

        ClientManager cliniqueMger = null;
        cliniqueMger = ClientManager.getInstance();

        PersonnelManager personnelManager = null;
        personnelManager = PersonnelManager.getInstance();

        Client c1 = new Client("Gonzales", "Pedro","10 rue de la banane", "11 rue de la frite", "35000",
                "Paris", "0123456789", "Oui","jean@lafritte.com","Trop Cute", true );

        Personnel p1 = new Personnel("Juan", "yolo", "adm", true);

        try {
            cliniqueMger.insertClient(c1);
            personnelManager.insertPers(p1);

            System.out.println("suppresion veterinaire");
            personnelManager.deletePers(p1);
            System.out.println("fin suppression pers");
        } catch (BLLException e) {
            e.printStackTrace();
        }



        String username = "Juan";
        String password = "yolo";

        mger.verifyUser(username,password);
    }
}
