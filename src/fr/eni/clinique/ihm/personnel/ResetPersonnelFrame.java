package fr.eni.clinique.ihm.personnel;

import fr.eni.clinique.bll.BLLException;
import fr.eni.clinique.bll.PersonnelManager;
import fr.eni.clinique.bll.SingletonGeneral;
import fr.eni.clinique.bo.Personnel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResetPersonnelFrame extends JInternalFrame implements ActionListener {
    private JPasswordField passwordField;
    private JLabel lblNom;
    private JLabel lblNomClient;
    private Personnel personnelToUpdate;
    public ResetPersonnelFrame(Personnel personnel) {


        super("Réinitialiser le mot de passe", false, true, true,false);
        personnelToUpdate = personnel;

        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        setBounds(100, 100,400, 250);
        getContentPane().setLayout(null);

        lblNom = new JLabel("Nom");
        lblNom.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNom.setBounds(37, 44, 46, 30);
        getContentPane().add(lblNom);

        lblNomClient = new JLabel(""+ personnelToUpdate.getNom());
        lblNomClient.setFont(new Font("Calibri", Font.BOLD, 14));
        lblNomClient.setBounds(164, 49, 201, 20);
        getContentPane().add(lblNomClient);

        JLabel lblMotDePasse = new JLabel("Nouveau Mot de passe");
        lblMotDePasse.setFont(new Font("Calibri", Font.BOLD, 14));
        lblMotDePasse.setBounds(10, 85, 156, 30);
        getContentPane().add(lblMotDePasse);

        JButton btnNewButton = new JButton("Valider");
        btnNewButton.setActionCommand("valider");
        btnNewButton.addActionListener(this);
        btnNewButton.setBounds(83, 170, 89, 23);
        getContentPane().add(btnNewButton);

        JButton btnAnnuler = new JButton("Annuler");
        btnAnnuler.setActionCommand("searchclient");
        btnAnnuler.addActionListener(this);
        btnAnnuler.setBounds(231, 170, 89, 23);
        getContentPane().add(btnAnnuler);

        passwordField = new JPasswordField();
        passwordField.setBounds(160, 88, 205, 25);
        getContentPane().add(passwordField);

    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "valider":
                System.out.println("Valider");
                resetPasswordPersonnel();
                this.dispose();
                break;
            case "annuler":
                System.out.println("Annuler");
                this.dispose();
                break;
            default:
                System.out.println("Probleme e=" + e);
        }
//        try {
//            SingletonGeneral.getInstance().getName().refresh());
//        } catch (BLLException e1) {
//            e1.printStackTrace();
//        }
    }

    public void resetPasswordPersonnel(){
        if(personnelToUpdate.getId() != null){
            PersonnelManager personnelManager = PersonnelManager.getInstance();
            personnelToUpdate.setMdp(String.valueOf(passwordField.getPassword()));
            System.out.println(String.valueOf(passwordField.getPassword()));
            try {
                personnelManager.updatePers(personnelToUpdate);
            } catch (BLLException e) {
                e.printStackTrace();
            }
        }else{
            SingletonGeneral.getInstance().getName().refresh();
        }

    }

    public void updateFrame(Personnel personnel){
        personnelToUpdate = personnel;
        lblNomClient.setText(personnel.getNom());
    }

}
