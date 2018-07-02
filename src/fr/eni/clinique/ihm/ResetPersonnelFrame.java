package fr.eni.clinique.ihm;

import javax.swing.*;
import java.awt.*;

public class ResetPersonnelFrame extends JInternalFrame {
    private JPasswordField passwordField;

    public ResetPersonnelFrame() {
        super("Réinitialiser le mot de passe", false, true, true,false);


        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(100, 100,400, 250);
        getContentPane().setLayout(null);

        JLabel lblNom = new JLabel("Nom");
        lblNom.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNom.setBounds(37, 44, 46, 30);
        getContentPane().add(lblNom);

        JLabel lblNom2 = new JLabel("Nom");
        lblNom2.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNom2.setBounds(164, 49, 201, 20);
        getContentPane().add(lblNom2);

        JLabel lblMotDePasse = new JLabel("Nouveau Mot de passe");
        lblMotDePasse.setFont(new Font("Calibri", Font.BOLD, 14));
        lblMotDePasse.setBounds(10, 85, 156, 30);
        getContentPane().add(lblMotDePasse);

        JButton btnNewButton = new JButton("Valider");
        btnNewButton.setBounds(83, 170, 89, 23);
        getContentPane().add(btnNewButton);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setBounds(231, 170, 89, 23);
        getContentPane().add(btnAnnuler);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 88, 205, 25);
        getContentPane().add(passwordField);

    }

}
