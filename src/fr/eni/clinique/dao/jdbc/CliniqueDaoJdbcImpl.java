package fr.eni.clinique.dao.jdbc;

import fr.eni.clinique.bo.Client;

import fr.eni.clinique.dao.CliniqueDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.JdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CliniqueDaoJdbcImpl implements CliniqueDAO {

    private static final String sqlInsertCli = "INSERT INTO Clients(NomClient,PrenomClient,Adresse1,Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String sqlUpdateCli = "INSERT INTO Clients(NomClient,PrenomClient,Adresse1,Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String sqlDeleteCli = "DELETE FROM Clients WHERE CodeClient=?";
    private static final String sqlSelectAll = "SELECT * FROM Clients ORDER BY NomClient";

    private static final String sqlInsertPers = "INSERT INTO Personnels(Nom, Role, Archive) values(?,?,?)";
    private static final String sqlUpdatePers = "INSERT INTO Personnels(Nom, Role, Archive) values(?,?,?)";
    private static final String sqlDeletePers = "DELETE FROM Personnels WHERE CodePers=?";
    private static final String sqlSelectPersbyCodePers = "Select CodePers, Nom, Role, Archive from Personnels where CodePers=?";
    @Override
    public void insert(Client client) throws DALException {
        //insert client
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlInsertCli);
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
}
