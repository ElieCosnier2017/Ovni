package fr.eni.clinique.dao.jdbc;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dao.ClientDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.JdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ClientDaoJdbcImpl implements ClientDAO {

    private static final String sqlInsertCli = "INSERT INTO Clients(NomClient,PrenomClient,Adresse1,Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String sqlUpdateCli = "INSERT INTO Clients(NomClient,PrenomClient,Adresse1,Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String sqlDeleteCli = "DELETE FROM Clients WHERE CodeClient=?";
    private static final String sqlSelectAll = "SELECT * FROM Clients ORDER BY NomClient";

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

    @Override
    public void update(Client c1) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlUpdateCli);
            rqt.setString(1, c1.getNomClient());
            rqt.setString(2, c1.getPrenomClient());
            rqt.setString(3, c1.getAdresse1());
            rqt.setString(4, c1.getAdresse2());
            rqt.setString(5, c1.getCodePostal());
            rqt.setString(6, c1.getVille());
            rqt.setString(7, c1.getNumTel());
            rqt.setString(8, c1.getAssurance());
            rqt.setString(9, c1.getEmail());
            rqt.setString(10, c1.getRemarque());
            rqt.setBoolean(11, c1.isArchive());
            rqt.executeUpdate();

        } catch (SQLException e) {
            throw new DALException("La mise a jour de : " + c1.getNomClient() + " " + c1.getPrenomClient() + " a échoué.", e);
        } finally {
            try {
                if (rqt != null){
                    rqt.close();
                }
                if(cnx !=null){
                    cnx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Client c1) throws DALException {

    }
}
