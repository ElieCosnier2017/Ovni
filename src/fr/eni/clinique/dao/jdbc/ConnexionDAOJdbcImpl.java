package fr.eni.clinique.dao.jdbc;

import fr.eni.clinique.bo.Personnel;
import fr.eni.clinique.dao.ConnexionDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.JdbcTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnexionDAOJdbcImpl implements ConnexionDAO {
    private static final String sqlGetUser = "Select CodePers, Nom, Role, Archive from Personnels where Nom=? and MotPasse=? and Archive=0";
    @Override
    public Personnel verifyUser(String username, String mdp) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        Personnel personnel = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlGetUser);
            rqt.setString(1, username);
            rqt.setString(2, mdp);

            rs = rqt.executeQuery();
            if (rs.next()){
                personnel = new Personnel(rs.getInt("CodePers"),
                        rs.getString("Nom"),
                        rs.getString("Role"),
                        rs.getBoolean("Archive"));

            }
        }catch (SQLException e)
        {
            throw new DALException("Requete de récupération de l'utilisateur à échouée", e);
        }
        return personnel;
    }

}
