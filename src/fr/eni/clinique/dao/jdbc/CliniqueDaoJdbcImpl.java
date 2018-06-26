package fr.eni.clinique.dao.jdbc;

import fr.eni.clinique.bo.Client;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dao.CliniqueDAO;
import fr.eni.clinique.dao.ConnexionDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.JdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CliniqueDaoJdbcImpl implements CliniqueDAO,ConnexionDAO {

    private static final String sqlInsert = "insert into Clients(NomClient,PrenomClient,Adresse1,Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String sqlGetUser = "Select CodePers, Nom, Role, Archive from Personnels where Nom=? and MotPasse=?";
    @Override
    public void insert(Client client) throws DALException {
        //insert client
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlInsert);
            rqt.setString(1, client.getNomClient());
            rqt.setString(2, client.getPrenomClient());
            rqt.setString(3, client.getAdresse1());
            rqt.setString(4, client.getAdresse2());
            rqt.setString(5, client.getCodePostal());
            rqt.setString(6, client.getVille());
            rqt.setString(7, client.getNumTel());
            rqt.setString(8, client.getAssurance());
            rqt.setString(9, client.getEmail());
            rqt.setString(10, client.getRemarque());
            rqt.setBoolean(11, client.isArchive());
            rqt.execute();
        } catch (SQLException e){
            throw new DALException("insert user failed", e);
        }
    }

    @Override
    public Personnel verifyUser(String username, String mdp) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        Personnel personnel = null;
        try {

            System.out.println("yolo1");
            cnx = JdbcTools.getConnection();
            System.out.println("yolo2");
            rqt = cnx.prepareStatement(sqlGetUser);
            System.out.println("yolo3");
            rqt.setString(1, username);
            System.out.println("yolo4");
            rqt.setString(2, mdp);
            System.out.println("yolo5");

            rs = rqt.executeQuery();
            System.out.println("yolo6");
            if (rs.next()){
                System.out.println("yolo7");
                personnel = new Personnel(rs.getInt("CodePers"),
                        rs.getString("Nom"), null,
                        rs.getString("Role"), rs.getBoolean("Archive"));

            }
        }catch (SQLException e)
        {
            throw new DALException("Requete de récu^pération de l'utilisateur à échouée", e);
        }
        return personnel;
    }
}
