package fr.eni.clinique.bll;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dao.AnimalDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.DAOfactory;

import java.util.ArrayList;
import java.util.List;

public class AnimalManager {

    private AnimalDAO animalDAO;
    private static AnimalManager instance=null;

    private AnimalManager(){
        //Instancier le Data Access Object
        animalDAO =DAOfactory.getAnimalDao();
    }

    public static synchronized AnimalManager getInstance(){
        if (instance == null){
            instance = new AnimalManager();
        }
        return instance;
    }

    public void insertAnimal(Animal ani) throws BLLException{
        if(ani.getCodeAnimal() != null){
            throw new BLLException("Animal deja existant.");
        }
        try{
            animalDAO.insert(ani);
        }catch (DALException e){
            throw new BLLException("Echec insertion animal - ",e);
        }
    }

    public void updateAnimal(Animal ani) throws BLLException{
        try{
            animalDAO.update(ani);
        }catch (DALException e){
            throw new BLLException("Echec mise à jour Animal - ",e);
        }
    }

    public void deleteAnimal(Animal ani) throws BLLException{
        try{
            animalDAO.delete(ani);
        }catch (DALException e){
            throw new BLLException("Echec supression animal - ",e);
        }
    }

    public List<Animal> selectAll() throws BLLException{
        List<Animal> animalList = new ArrayList<>();
        try{
            animalList = animalDAO.selectAll();
        }catch (DALException e){
            throw new BLLException("Echec recuperation animal - ",e);

        }
        return  animalList;
    }

    public List<Animal> selectAllByClient(int codeClient) throws BLLException{
        List<Animal> animalList = new ArrayList<>();
        try{
            animalList = animalDAO.selectAllByClient(codeClient);
        }catch (DALException e){
            throw new BLLException("Echec recuperation animaux - ",e);

        }
        return  animalList;
    }

    public Animal selectOne(int codeAnimal) throws BLLException{
        Animal animal = null;
        try{
            animal = animalDAO.selectOne(codeAnimal);
        }catch (DALException e){
            throw new BLLException("Echec recuperation animal - ",e);

        }
        return animal;
    }


}
