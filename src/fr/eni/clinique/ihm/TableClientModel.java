package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bo.Client;
import fr.eni.clinique.bo.Personnel;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class TableClientModel extends AbstractTableModel {
    private final List<Client> clients = new ArrayList<>();
    private final ClientManager clientManager = ClientManager.getInstance();
    private final String[] entetes = {"  ", "Fonction", "Mot de passe"};

    public TableClientModel() throws BLLException {
        super();
        List<Client> clientList = clientManager.getAllClients();
        for(Client cli : clientList){
            clients.add(cli);
        }
    }

    public int getRowCount() {
        return clients.size();
    }

    public int getColumnCount() {
        return entetes.length;
    }

    public String getColumnName(int columnIndex) {
        return entetes[columnIndex];
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
//            case 0:
//                return clients.get(rowIndex).g();
//            case 1:
//                return clients.get(rowIndex).getRole();
//            case 2:
//                return clients.get(rowIndex).getMdp();
            default:
                return null; //Ne devrait jamais arriver
        }
    }

    public void addAmi(Client ami) {
        clients.add(ami);

        fireTableRowsInserted(clients.size() - 1, clients.size() - 1);
    }

    public void removeAmi(int rowIndex) {
        clients.remove(rowIndex);

        fireTableRowsDeleted(rowIndex, rowIndex);
    }
}