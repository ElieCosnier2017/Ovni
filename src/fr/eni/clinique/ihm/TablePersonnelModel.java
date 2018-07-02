package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TablePersonnelModel extends AbstractTableModel {
    private final List<Personnel> personnel = new ArrayList<>();

    private final String[] entetes = {"Nom", "Fonction", "Mot de passe"};

    public TablePersonnelModel() throws BLLException {
        super();

        PersonnelManager personnelManager;
        personnelManager = PersonnelManager.getInstance();
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

    public void addAmi(Personnel ami) {
        personnel.add(ami);

        fireTableRowsInserted(personnel.size() - 1, personnel.size() - 1);
    }

    public void removeAmi(int rowIndex) {
        personnel.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}
