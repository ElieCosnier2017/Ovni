package fr.eni.clinique.ihm;

import fr.eni.clinique.bo.Personnel;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TablePersonnelModel extends AbstractTableModel {
    private final List<Personnel> personnel = new ArrayList<Personnel>();

    private final String[] entetes = {"Nom", "Fonction", "Mot de passe"};

    public TablePersonnelModel() {
        super();

        personnel.add(new Personnel("Johnathan", "Sykes", "hello"));
//            amis.add(new Ami("Nicolas", "Van de Kampf", Color.black, true, Sport.FOOTBALL));
//            amis.add(new Ami("Damien", "Cuthbert", Color.cyan, true, Sport.RIEN));
//            amis.add(new Ami("Corinne", "Valance", Color.blue, false, Sport.NATATION));
//            amis.add(new Ami("Emilie", "Schrödinger", Color.magenta, false, Sport.FOOTBALL));
//            amis.add(new Ami("Delphine", "Duke", Color.yellow, false, Sport.TENNIS));
//            amis.add(new Ami("Eric", "Trump", Color.pink, true, Sport.FOOTBALL));
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
            case 1:
                return personnel.get(rowIndex).getNom();
            case 2:
                return personnel.get(rowIndex).getRole();
            case 3:
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