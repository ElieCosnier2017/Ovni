package fr.eni.clinique.dao;

import fr.eni.clinique.bo.Client;

import java.sql.SQLException;

public class AppliTestDAL {


    public static void main(String[] args) throws SQLException {
        CliniqueDAO cliniqueDAO = DAOfactory.getCliniqueDao();

        Client c1 = new Client("Gonzales", "Pedro","10 rue de la banane", "11 rue de la frite", "35000",
                "Paris", "0123456789", "Oui","jean@lafritte.com","Trop Cute", true );

        try {
            System.out.println("Ajout d'un client : ----");
            cliniqueDAO.insert(c1);
            System.out.println("Fin Ajout d'un client : ----");
        } catch (SQLException e){
            System.out.println("Erreur ajout client");
            throw new SQLException(e);
        }

    }




}
