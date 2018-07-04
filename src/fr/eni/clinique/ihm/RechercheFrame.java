package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.ClientManager;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bll.SingletonGeneral;
import fr.eni.clinique.bo.Client;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class RechercheFrame extends JInternalFrame implements ActionListener {
    private JTextField nom;
    private JTextField motPasse;
    private JComboBox comboBox;
    private JTextField recherche;
    private JTable table;

    public RechercheFrame() throws BLLException {
        //Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
        super("Trouver un client", false, true, true,false);


        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(100, 100,700, 400);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 670, 75);
        panel.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        getContentPane().add(panel);
        panel.setLayout(null);

        recherche = new JTextField();
        recherche.setBounds(46, 11, 454, 52);
        panel.add(recherche);
        recherche.setColumns(10);

        JButton btnRecherche = new JButton("Rechercher");
        btnRecherche.setActionCommand("recherche");
        btnRecherche.addActionListener(this);
        btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/search.png")));
        btnRecherche.setBounds(530, 11, 120, 48);
        panel.add(btnRecherche);

        table = new JTable(new TableClientModel());
        table.setShowGrid(false);
        table.setBounds(43, 200, 1081, 400);

        JScrollPane scrollPane2 = new JScrollPane(table);
        scrollPane2.setAutoscrolls(true);

        table.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent mouseEvent) {
                GeneralFrame ecran = null;
                try {
                    ecran = new GeneralFrame(SingletonGeneral.getInstance().getPersonnelGeneral());
                } catch (BLLException e) {
                    e.printStackTrace();
                }
                ecran.setVisible(true);
                SingletonGeneral.getInstance().getName().dispose();
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 1 && table.getSelectedRow() != -1) {
                    try {
                        TableClientModel tableClientModel = new TableClientModel();
                        List<String> attributs = tableClientModel.clientValuesAt(table.getSelectedRow());
//                        Client client = new Client("Cosnier","Elie","11 rue de la banane","","25000","Rennes","02456987","oui","elflflfl","",false);
//                        ecran.panelClient(client);
//                        ecran.repaint();

                        ecran.fillTextFieldClient(attributs.get(0), attributs.get(1));
                    } catch (BLLException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        scrollPane2.setBounds(10, 100, 670, 177);
        getContentPane().add(scrollPane2);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "recherche":
                rechercheCli();
                break;
            default:
                System.out.println("Probleme e=" + e);
        }
    }

    private void rechercheCli(){
        String name = recherche.getText();
        System.out.println(name);
        ClientManager clientManager = ClientManager.getInstance();
        List<Client> clientList = new ArrayList<>();
        try {
            clientList = clientManager.findClientsByName(name);
        } catch (BLLException e) {
            e.printStackTrace();
        }

        try {
            System.out.println(clientList.size());
            TableClientModel tableClientModel = new TableClientModel(clientList);
            table.setModel(tableClientModel);
        } catch (BLLException e) {
            e.printStackTrace();
        }

    }


}
