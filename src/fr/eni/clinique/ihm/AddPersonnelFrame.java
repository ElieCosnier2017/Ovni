package fr.eni.clinique.ihm;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bll.SingletonGeneral;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddPersonnelFrame extends JInternalFrame implements ActionListener {
    private JTextField nom;
    private JTextField motPasse;
    private JComboBox comboBox;
    PersonnelManager personnelManager = PersonnelManager.getInstance();

    public AddPersonnelFrame() {
        //Ecran avec un titre, redimensionable, fermable, agrandissable, iconifiable
        super("Ajouter un personnel", false, true, true,false);

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(100, 100,400, 300);
        getContentPane().setLayout(null);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNom.setBounds(57, 44, 46, 14);
        getContentPane().add(lblNom);

        nom = new JTextField();
        nom.setBounds(164, 41, 201, 20);
        getContentPane().add(nom);
        nom.setColumns(10);

        JLabel lblFonction = new JLabel("Fonction");
        lblFonction.setFont(new Font("Calibri", Font.BOLD, 14));
        lblFonction.setBounds(57, 88, 71, 14);
        getContentPane().add(lblFonction);

        motPasse = new JTextField();
        motPasse.setColumns(10);
        motPasse.setBounds(164, 129, 201, 20);
        getContentPane().add(motPasse);

        JLabel lblMotDePasse = new JLabel("Mot de passe");
        lblMotDePasse.setFont(new Font("Calibri", Font.BOLD, 14));
        lblMotDePasse.setBounds(57, 132, 95, 14);
        getContentPane().add(lblMotDePasse);

        comboBox = new JComboBox();
        comboBox.addItem("adm");
        comboBox.addItem("vet");
        comboBox.addItem("sec");
        comboBox.setBounds(164, 85, 201, 20);
        getContentPane().add(comboBox);

        JButton btnNewButton = new JButton("Valider");
        btnNewButton.setActionCommand("valider");
        btnNewButton.addActionListener(this);
        btnNewButton.setBounds(83, 203, 89, 23);
        getContentPane().add(btnNewButton);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setActionCommand("annuler");
        btnAnnuler.addActionListener(this);
        btnAnnuler.setBounds(231, 203, 89, 23);
        getContentPane().add(btnAnnuler);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "valider":
                System.out.println("Valider");
                ajouterPersonnel();
                this.dispose();
                break;
            case "annuler":
                System.out.println("Annuler");
                this.dispose();
                break;
            default:
                System.out.println("Probleme e=" + e);
        }
        try {
            GeneralFrame ecran = new GeneralFrame(SingletonGeneral.getInstance().getPersonnelGeneral());
            ecran.setVisible(true);
        } catch (BLLException e1) {
            e1.printStackTrace();
        }
        SingletonGeneral.getInstance().getName().dispose();
    }

    private void ajouterPersonnel() {
        String name = nom.getText();
        String motDePasse = motPasse.getText();
        String role = comboBox.getSelectedItem().toString();
        Personnel personnel = new Personnel(name,motDePasse,role);

        try {
            PersonnelManager.getInstance().insertPers(personnel);
            TablePersonnelModel tablePersonnelModel = new TablePersonnelModel();
            tablePersonnelModel.fireTableDataChanged();
        } catch (BLLException e) {
            e.printStackTrace();
        }
    }


}
