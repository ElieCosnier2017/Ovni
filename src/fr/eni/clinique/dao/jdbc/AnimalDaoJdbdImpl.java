package fr.eni.clinique.dao.jdbc;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dao.AnimalDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.JdbcTools;

import java.sql.*;
import java.util.List;

public class AnimalDaoJdbdImpl implements AnimalDAO {

    private static final String sqlInsertAni = "INSERT INTO Animal(NomAnimal,Sexe,Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive) values(?,?,?,?,?,?,?,?,?)";
    private static final String sqlUpdateAni = "UPDATE Animal SET NomAnimal=?,Sexe=?,Couleur=?,Race=?, Espece=?, CodeClient=?, Tatouage=?, Antecedents=?, Archive=? WHERE CodeAnimal=?";
    private static final String sqlDeleteAni = "UPDATE Clients SET Archive=1 WHERE CodeAnimal=?";


    @Override
    public List<Animal> selectAll() throws DALException {
        return null;
    }

    @Override
    public void insert(Animal a1) throws DALException {
        //insert client
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlInsertAni, Statement.RETURN_GENERATED_KEYS);
            rqt.setString(1, a1.getNomAnimal());
            rqt.setString(2, String.valueOf(a1.getSexe()));
            rqt.setString(3, a1.getCouleur());
            rqt.setString(4, a1.getRace());
            rqt.setString(5, a1.getEspece());
            rqt.setLong(6, a1.getCodeClient());
            rqt.setString(7, a1.getTatouage());
            rqt.setString(8, a1.getAntecedents());
            rqt.setBoolean(9, a1.getArchive());

            rqt.execute();

            ResultSet rs = rqt.getGeneratedKeys();
            if(rs.next())
            {
                a1.setCodeAnimal(rs.getInt(1));
            }
        } catch (SQLException e){
            throw new DALException("insert animal failed", e);
        }
    }

    @Override
    public void update(Animal a1) throws DALException {
        //insert client
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlUpdateAni);
            rqt.setString(1, a1.getNomAnimal());
            rqt.setString(2, String.valueOf(a1.getSexe()));
            rqt.setString(3, a1.getCouleur());
            rqt.setString(4, a1.getRace());
            rqt.setString(5, a1.getEspece());
            rqt.setLong(6, a1.getCodeClient());
            rqt.setString(7, a1.getTatouage());
            rqt.setString(8, a1.getAntecedents());
            rqt.setBoolean(9, a1.getArchive());
            rqt.execute();
        } catch (SQLException e) {
            throw new DALException("update animal failed", e);        }
    }

        @Override
    public void delete(Animal a1) throws DALException {

    }
}
