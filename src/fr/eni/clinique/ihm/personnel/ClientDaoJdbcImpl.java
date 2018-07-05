package fr.eni.clinique.ihm.personnel;

import fr.eni.clinique.bo.Client;
import fr.eni.clinique.dao.ClientDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.JdbcTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoJdbcImpl implements ClientDAO {

    private static final String sqlInsertCli = "INSERT INTO Clients(NomClient,PrenomClient,Adresse1,Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive) values(?,?,?,?,?,?,?,?,?,?,?)";
    private static final String sqlUpdateCli = "UPDATE Clients SET NomClient=?,PrenomClient=?,Adresse1=?,Adresse2=?, CodePostal=?, Ville=?, NumTel=?, Assurance=?," +
            " Email=?, Remarque=?, Archive=? WHERE CodeClient=?";
    private static final String sqlDeleteCli = "UPDATE Clients SET Archive=1 WHERE CodeClient=?";
    private static final String sqlSelectAll = "SELECT * FROM Clients WHERE Archive=0 ORDER BY NomClient";
    private static final String sqlSelectOne = "SELECT CodeClient,NomClient,PrenomClient,Adresse1,Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive FROM Clients Where CodeClient=?";
    private static final String sqlSelectOneByName = "SELECT CodeClient,NomClient,PrenomClient,Adresse1,Adresse2, CodePostal, Ville, NumTel, Assurance, Email, Remarque, Archive FROM Clients Where NomClient=?";
    private static final String sqlSelectAllByName = "SELECT NomClient, PrenomClient, CodePostal, Ville FROM Clients WHERE Archive=0 AND NomClient like ? ORDER BY NomClient";


    @Override
    public void insert(Client client) throws DALException {
        //insert client
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlInsertCli, Statement.RETURN_GENERATED_KEYS);
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
            rqt.setBoolean(11, false);
            rqt.execute();

            ResultSet rs = rqt.getGeneratedKeys();
            if(rs.next())
            {
                client.setCodeClient(rs.getInt(1));
            }
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
            rqt.setInt(12,c1.getCodeClient());
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
    public void delete(Client data) throws DALException {

    }

    @Override
    public void delete(Integer codeClient) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlDeleteCli);
            rqt.setInt(1, codeClient);
            rqt.execute();
        }catch (SQLException e){
            throw new DALException("La suppression de : " + codeClient + " a échoué.", e);
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
    public List<Client> selectAll() throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        List<Client> clientList = new ArrayList<>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectAll);
            rs = rqt.executeQuery();
            while (rs.next()){
                Client client = new Client(
                        rs.getInt("CodeClient"),
                        rs.getString("NomClient"),
                        rs.getString("PrenomClient"),
                        rs.getString("Adresse1"),
                        rs.getString("Adresse2"),
                        rs.getString("CodePostal"),
                        rs.getString("Ville"),
                        rs.getString("NumTel"),
                        rs.getString("Assurance"),
                        rs.getString("Email"),
                        rs.getString("Remarque"),
                        rs.getBoolean("Archive")
                );
                clientList.add(client);
            }

        }catch (SQLException e){
            throw new DALException("La récupération des clients a échoué.", e);
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
        return clientList;

    }

    @Override
    public Client selectOne(Integer codeClient) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        Client client = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectOne);
            rqt.setInt(1, codeClient);
            rs = rqt.executeQuery();
            if (rs.next()) {
                 client = new Client(
                        rs.getInt("CodeClient"),
                        rs.getString("NomClient"),
                        rs.getString("PrenomClient"),
                        rs.getString("Adresse1"),
                        rs.getString("Adresse2"),
                        rs.getString("CodePostal"),
                        rs.getString("Ville"),
                        rs.getString("NumTel"),
                        rs.getString("Assurance"),
                        rs.getString("Email"),
                        rs.getString("Remarque"),
                        rs.getBoolean("Archive")
                );
            }
        }catch (SQLException e){
            throw new DALException("La récupération du client " + codeClient + " a échoué.", e);
        } finally {
            try {
                if (rqt != null) {
                    rqt.close();
                }
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    @Override
    public Client selectOneByName(String nomClient) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        Client client = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectOneByName);
            rqt.setString(1, nomClient);
            rs = rqt.executeQuery();
            if (rs.next()) {
                client = new Client(
                        rs.getInt("CodeClient"),
                        rs.getString("NomClient"),
                        rs.getString("PrenomClient"),
                        rs.getString("Adresse1"),
                        rs.getString("Adresse2"),
                        rs.getString("CodePostal"),
                        rs.getString("Ville"),
                        rs.getString("NumTel"),
                        rs.getString("Assurance"),
                        rs.getString("Email"),
                        rs.getString("Remarque"),
                        rs.getBoolean("Archive")
                );
            }
        }catch (SQLException e){
            throw new DALException("La récupération du client " + nomClient + " a échoué.", e);
        } finally {
            try {
                if (rqt != null) {
                    rqt.close();
                }
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return client;
    }

    @Override
    public List<Client> findByName(String name) throws DALException {
        Connection cnx = null;
        ResultSet rs = null;
        PreparedStatement rqt = null;
        Client client = null;
        List<Client> clientList = new ArrayList<>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectAllByName);
            rqt.setString(1, name + '%');

            rs = rqt.executeQuery();

            while (rs.next()){
                client = new Client(
                        rs.getString("NomClient"),
                        rs.getString("PrenomClient"),
                        rs.getString("CodePostal"),
                        rs.getString("Ville"));
                clientList.add(client);
            }
        }catch (SQLException e){
            throw new DALException("Erreur récupération Client lors de la connection", e);
        } finally {
            try {
                if (rqt != null) {
                    rqt.close();
                }
                if (cnx != null) {
                    cnx.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return clientList;
    }
}
