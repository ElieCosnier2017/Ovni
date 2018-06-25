package fr.eni.clinique.bll;

import fr.eni.clinique.dao.CliniqueDAO;
import fr.eni.clinique.dao.DAOfactory;

public class CliniqueManager {

    private CliniqueDAO daoClinique;

    private CliniqueManager(){
        //Instancier le Data Access Object
        daoClinique =DAOfactory.getCliniqueDao();
    }
}
