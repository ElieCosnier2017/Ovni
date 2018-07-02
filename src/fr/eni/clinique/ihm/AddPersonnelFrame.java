package fr.eni.clinique.ihm;

import javax.swing.*;
import java.awt.*;

public class AddPersonnelFrame extends JInternalFrame {
    private JTextField textField;
    private JTextField textField_1;

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

        textField = new JTextField();
        textField.setBounds(164, 41, 201, 20);
        getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblFonction = new JLabel("Fonction");
        lblFonction.setFont(new Font("Calibri", Font.BOLD, 14));
        lblFonction.setBounds(57, 88, 71, 14);
        getContentPane().add(lblFonction);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(164, 129, 201, 20);
        getContentPane().add(textField_1);

        JLabel lblMotDePasse = new JLabel("Mot de passe");
        lblMotDePasse.setFont(new Font("Calibri", Font.BOLD, 14));
        lblMotDePasse.setBounds(57, 132, 95, 14);
        getContentPane().add(lblMotDePasse);

        JComboBox comboBox = new JComboBox();
        comboBox.setBounds(164, 85, 201, 20);
        getContentPane().add(comboBox);

        JButton btnNewButton = new JButton("Valider");
        btnNewButton.setBounds(83, 203, 89, 23);
        getContentPane().add(btnNewButton);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setBounds(231, 203, 89, 23);
        getContentPane().add(btnAnnuler);

    }

}
