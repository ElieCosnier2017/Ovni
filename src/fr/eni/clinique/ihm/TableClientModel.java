package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableClientModel extends AbstractTableModel {
    private final List<Client> client = new ArrayList<>();
    private final ClientManager clientManager = ClientManager.getInstance();
    private final String[] entetes = {"Nom", "Prénom", "Code Postal", "Mot de passe"};

    public TableClientModel() throws BLLException {
        super();

        List<Client> personnelList = clientManager.getAllClients();
        for(Client cli : personnelList){
            client.add(cli);
        }
    }

    public int getRowCount() {
        return client.size();
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
                return client.get(rowIndex).getNomClient();
            case 1:
                return client.get(rowIndex).getPrenomClient();
            case 2:
                return client.get(rowIndex).getCodePostal();
            case 3:
                return client.get(rowIndex).getVille();
            default:
                return null; //Ne devrait jamais arriver
        }
    }

    public void addAmi(Client ami) {
        client.add(ami);

        fireTableRowsInserted(client.size() - 1, client.size() - 1);
    }

    public void removeAmi(int rowIndex) {
        client.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}