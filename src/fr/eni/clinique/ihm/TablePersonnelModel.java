package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TablePersonnelModel extends AbstractTableModel {
    private final List<Personnel> personnel = new ArrayList<>();
    private final PersonnelManager personnelManager = PersonnelManager.getInstance();
    private final String[] entetes = {"Nom", "Fonction", "Mot de passe"};

    public TablePersonnelModel() throws BLLException {
        super();
        List<Personnel> personnelList = personnelManager.selectAllPers();
        for(Personnel pers : personnelList){
            pers.setMdp("********");
            personnel.add(pers);
        }
    }

    public int getRowCount() {
        return personnel.size();
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
                return personnel.get(rowIndex).getNom();
            case 1:
                return personnel.get(rowIndex).getRole();
            case 2:
                return personnel.get(rowIndex).getMdp();
            default:
                return null; //Ne devrait jamais arriver
        }
    }

    public void addPersonnel(Personnel pers) {
        personnel.add(pers);

        fireTableRowsInserted(personnel.size() - 1, personnel.size() - 1);
    }

    public void removePersonnel(int rowIndex) throws BLLException {
        String name;
        String role;
        name = getValueAt(rowIndex,0).toString();
        role = getValueAt(rowIndex, 1).toString();
        Personnel pers = personnelManager.selectOneByNameAndRole(name,role);
        personnel.remove(pers);
        personnelManager.deletePers(pers);


    }
}
