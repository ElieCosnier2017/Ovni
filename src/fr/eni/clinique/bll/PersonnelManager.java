package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.DAOfactory;
import fr.eni.clinique.dao.PersonnelDAO;
import fr.eni.clinique.dao.RdvDAO;

import java.util.Date;
import java.util.List;

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

    public void updatePers(Personnel p1) throws BLLException {
        try{
            personnelDAO.update(p1);
        }catch (DALException e){
            throw new BLLException("Echec mise à jour pers - ",e);
        }
    }

    public void deletePers(Personnel p1) throws BLLException {
        try{
            RdvDAO rdvDAO = DAOfactory.getRdvDao();
            Boolean futureRdv = false;
            if(p1.getRole().equals("vet")){
                List<Rdv> rdvList = rdvDAO.getListRdvByVet(p1.getId());
                for (Rdv rendezVous : rdvList){
                    Date date = new Date();
                    if(rendezVous.getDateRdv().after(date)){
                        futureRdv = true;
                        System.out.println("Impossible de supprimer ce veterinaire il lui reste des rdv");
                    }
                }
                if(!futureRdv){
                    personnelDAO.delete(p1);
                }
            }
        }catch (DALException e){
            throw new BLLException("Echec suppression pers - ",e);
        }
    }

    public void selectAllPers(Personnel p1) throws BLLException {
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
