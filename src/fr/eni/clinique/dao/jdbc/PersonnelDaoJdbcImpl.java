package fr.eni.clinique.dao.jdbc;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.JdbcTools;
import fr.eni.clinique.dao.PersonnelDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonnelDaoJdbcImpl implements PersonnelDAO {

    private static final String sqlInsertPers = "INSERT INTO Personnels(Nom, MotPasse, Role, Archive) values(?,?,?,?)";
    private static final String sqlUpdatePers = "UPDATE Personnels set Nom=?, MotPasse=?, Role=?, Archive=? WHERE CodePers=?";
    private static final String sqlUpdateMotPasse = "UPDATE Personnels set MotPasse=? Where CodePers=?";
    private static final String sqlDeletePers = "DELETE FROM Personnels WHERE CodePers=?";
    private static final String sqlSelectAllPers = "Select CodePers, Nom, Role, Archive from Personnels";
    private static final String sqlSelectPersbyCodePers = "Select CodePers, Nom, Role, Archive from Personnels where CodePers=?";

    @Override
    public Personnel selectOne(int codePers) throws DALException{
        Connection cnx = null;
        ResultSet rs = null;
        PreparedStatement rqt = null;
        Personnel personnel = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectPersbyCodePers);
            rqt.setInt(1, codePers);
            rs = rqt.executeQuery();

            if(rs.next()){
               personnel = new Personnel(
                        rs.getInt("CodePers"),
                        rs.getString("Nom"),
                        rs.getString("Role"),
                        rs.getBoolean("Archive"));
            }
        }catch (SQLException e){
            throw new DALException("Erreur récupération liste du personnel", e);
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
        return personnel;
    }


    @Override
    public List<Personnel> selectAll() throws DALException{
        Connection cnx = null;
        ResultSet rs = null;
        PreparedStatement rqt = null;
        List<Personnel> personnelList = new ArrayList<>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectAllPers);
            rs = rqt.executeQuery();

            if(rs.next()){
                Personnel personnel = new Personnel(
                        rs.getInt("CodePers"),
                        rs.getString("Nom"),
                        rs.getString("Role"),
                        rs.getBoolean("Archive"));

                personnelList.add(personnel);
            }
        }catch (SQLException e){
            throw new DALException("Erreur récupération liste du personnel", e);
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
        return personnelList;
    }


    @Override
    public void insert(Personnel pers) throws DALException {
        //insert client
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlInsertPers, Statement.RETURN_GENERATED_KEYS);
            rqt.setString(1, pers.getNom());
            rqt.setString(2, pers.getMdp());
            rqt.setString(3, pers.getRole());
            rqt.setBoolean(4, pers.isArchive());
            rqt.execute();

            ResultSet rs = rqt.getGeneratedKeys();
            if(rs.next())
            {
                pers.setId(rs.getInt(1));
            }
        } catch (SQLException e){
            throw new DALException("insertion personnel erreur", e);
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
    }

    @Override
    public void update(Personnel p1) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlUpdatePers);
            rqt.setString(1, p1.getNom());
            rqt.setString(2, p1.getMdp());
            rqt.setString(3, p1.getRole());
            rqt.setBoolean(4, p1.isArchive());
            rqt.executeUpdate();

        } catch (SQLException e) {
            throw new DALException("La mise a jour de : " + p1.getNom() + " a échoué.", e);
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
    public void delete(Personnel p1) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlDeletePers);
            rqt.setInt(1, p1.getId());
            rqt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("Suppression de id=" + p1.getId() + " à échouée", e);
        } finally {
            try {
                if (rqt != null){
                    rqt.close();
                }
                if(cnx!=null){
                    cnx.close();
                }
            } catch (SQLException e) {
                throw new DALException("close failed " , e);
            }
        }
    }
}
