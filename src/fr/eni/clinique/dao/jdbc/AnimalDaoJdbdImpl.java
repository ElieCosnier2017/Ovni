package fr.eni.clinique.dao.jdbc;

import fr.eni.clinique.bo.Animal;
import fr.eni.clinique.dao.AnimalDAO;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.JdbcTools;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalDaoJdbdImpl implements AnimalDAO {

    private static final String sqlInsertAni = "INSERT INTO Animaux(NomAnimal,Sexe,Couleur, Race, Espece, CodeClient, Tatouage, Antecedents, Archive) values(?,?,?,?,?,?,?,?,?)";
    private static final String sqlUpdateAni = "UPDATE Animaux SET NomAnimal=?,Sexe=?,Couleur=?,Race=?, Espece=?, CodeClient=?, Tatouage=?,  Archive=? WHERE CodeAnimal=?";
    private static final String sqlDeleteAni = "UPDATE Animaux SET Archive=1 WHERE CodeAnimal=?";
    private static final String sqlSelectAll = "SELECT * FROM Animaux WHERE Archive=0 ORDER BY NomAnimal";
    private static final String sqlSelectOne = "SELECT * FROM Animaux WHERE Archive=0 And CodeAnimal=?";
    private static final String sqlSelectAllByClient = "SELECT * FROM Animaux WHERE Archive=0 AND CodeClient=? ORDER BY NomAnimal";



    @Override
    public List<Animal> selectAll() throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        List<Animal> animalList = new ArrayList<>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectAll);
            rs = rqt.executeQuery();
            while (rs.next()){
                Animal animal = new Animal(
                        rs.getInt("CodeAnimal"),
                        rs.getString("NomAnimal"),
                        rs.getString("Sexe"),
                        rs.getString("Couleur"),
                        rs.getString("Race"),
                        rs.getString("Espece"),
                        rs.getLong("CodeClient"),
                        rs.getString("Tatouage"),
                        rs.getString("Antecedents"),
                        rs.getBoolean("Archive"));

                animalList.add(animal);
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
        return animalList;

    }

    @Override
    public List<Animal> selectAllByClient(int codeClient) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        List<Animal> animalList = new ArrayList<>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectAllByClient);
            rqt.setInt(1 ,codeClient );
            rs = rqt.executeQuery();
            while (rs.next()){
                Animal animal = new Animal(
                        rs.getInt("CodeAnimal"),
                        rs.getString("NomAnimal"),
                        rs.getString("Sexe"),
                        rs.getString("Couleur"),
                        rs.getString("Race"),
                        rs.getString("Espece"),
                        rs.getLong("CodeClient"),
                        rs.getString("Tatouage"),
                        rs.getString("Antecedents"),
                        rs.getBoolean("Archive"));

                animalList.add(animal);
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
        return animalList;

    }

    @Override
    public Animal selectOne(int codeAnimal) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        Animal animal = null;
        System.out.println(codeAnimal);
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectOne);
            rqt.setInt(1, codeAnimal);

            rs =  rqt.executeQuery();
            if(rs.next())
            {
                 animal = new Animal(
                        rs.getInt("CodeAnimal"),
                        rs.getString("NomAnimal"),
                        rs.getString("Sexe"),
                        rs.getString("Couleur"),
                        rs.getString("Race"),
                        rs.getString("Espece"),
                        rs.getLong("CodeClient"),
                        rs.getString("Tatouage"),
                        rs.getString("Antecedents"),
                        rs.getBoolean("Archive"));
            }
        } catch (SQLException e){
            throw new DALException("get animal failed", e);
        }
        return animal;
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
            rqt.setBoolean(8, a1.getArchive());
            rqt.setInt(9,a1.getCodeAnimal());

            rqt.executeUpdate();
        } catch (SQLException e) {
            throw new DALException("update animal failed", e);        }
    }

        @Override
    public void delete(Animal a1) throws DALException {

    }
}
