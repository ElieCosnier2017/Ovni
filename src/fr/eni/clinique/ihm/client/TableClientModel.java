package fr.eni.clinique.ihm.client;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Client;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableClientModel extends AbstractTableModel {
    private List<Client> client = new ArrayList<>();
    private final ClientManager clientManager = ClientManager.getInstance();
    private final String[] entetes = {"Nom", "Prénom", "Code Postal", "Ville"};

    public TableClientModel() throws BLLException {
        super();
        List<Client> clientList = clientManager.getAllClients();
        for(Client cli : clientList){
            client.add(cli);
        }
    }

    public TableClientModel(List<Client> clientList) throws BLLException {
        super();
        for(Client cli : clientList){
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

    public List<String> clientValuesAt(int index){
        String name = getValueAt(index, 0).toString();
        String firstname = getValueAt(index, 1).toString();

        List<String> attributList = new ArrayList<>();
        attributList.add(name);
        attributList.add(firstname);
        return attributList;
    }
}