package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RechercheFrame extends JInternalFrame implements ActionListener {
    private JTextField nom;
    private JTextField motPasse;
    private JComboBox comboBox;

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

        JTextField textField = new JTextField();
        textField.setBounds(46, 11, 454, 52);
        panel.add(textField);
        textField.setColumns(10);

        JButton btnRecherche = new JButton("Rechercher");
        btnRecherche.setIcon(new ImageIcon(this.getClass().getResource("/fr/eni/clinique/Ressources/search.png")));
        btnRecherche.setBounds(530, 11, 120, 48);
        panel.add(btnRecherche);

        JTable table = new JTable(new TableClientModel());
        table.setShowGrid(false);
        table.setBounds(43, 200, 1081, 400);

        JScrollPane scrollPane2 = new JScrollPane(table);
        scrollPane2.setAutoscrolls(true);

        scrollPane2.setBounds(10, 100, 670, 177);
        getContentPane().add(scrollPane2);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "valider":
                System.out.println("yoValider");
                break;
            case "annuler":
                System.out.println("yoAnnuler");
                this.dispose();
                break;
            default:
                System.out.println("Probleme e=" + e);
        }
    }


}
