package fr.eni.clinique.ihm.animal;

import fr.eni.clinique.bll.AnimalManager;
import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dao.AnimalDAO;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableAnimalModel extends AbstractTableModel {
    private List<Animal> animal = new ArrayList<>();
    private final AnimalManager animalManager = AnimalManager.getInstance();
    private final String[] entetes = {"Numéro","Nom", "Sexe", "Couleur", "Race", "Espèce", "Tatouage"};

    public TableAnimalModel(Client client) throws BLLException {
        super();
        if(client != null){
            List<Animal> animauxList = animalManager.selectAllByClient(client.getCodeClient());
            for(Animal ani : animauxList){
                animal.add(ani);
            }
        }
    }


    public int getRowCount() {
        return animal.size();
    }

    public int getColumnCount() {
        return entetes.length;
    }

    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return animal.get(rowIndex).getCodeAnimal();
            case 1:
                return animal.get(rowIndex).getNomAnimal();
            case 2:
                return animal.get(rowIndex).getSexe();
            case 3:
                return animal.get(rowIndex).getCouleur();
            case 4:
                return animal.get(rowIndex).getRace();
            case 5:
                return animal.get(rowIndex).getEspece();
            case 6:
                return animal.get(rowIndex).getTatouage();

            default:
                return null; //Ne devrait jamais arriver
        }
    }


    public Animal getAnimalSelect(int selection) {
        Animal animal = null;
        try {
            AnimalManager animalManager = AnimalManager.getInstance();
            Integer codeAnimal = (Integer) getValueAt(selection,0);
            animal = animalManager.selectOne(codeAnimal);
        } catch (BLLException e) {
            e.printStackTrace();
        }
        return animal;
    }

    public void addAmi(Animal ami) {
        animal.add(ami);

        fireTableRowsInserted(animal.size() - 1, animal.size() - 1);
    }

    public void removeAmi(int rowIndex) {
        animal .remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}