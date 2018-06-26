package fr.eni.clinique.dao.jdbc;

import fr.eni.clinique.bo.Client;

import fr.eni.clinique.dao.CliniqueDAO;
import fr.eni.clinique.dao.JdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CliniqueDaoJdbcImpl implements CliniqueDAO {

    private static final String sqlInsert = "insert into Clients(NomClient,PrenomClient,Adresse1,Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) values(?,?,?,?,?,?,?,?,?,?,?)";
    @Override
    public void insert(Client client) throws SQLException {
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
            throw new SQLException(e);
        }
    }
}
