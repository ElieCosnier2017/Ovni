package fr.eni.clinique.dao.jdbc;

import fr.eni.clinique.bo.Rdv;
import fr.eni.clinique.dao.DALException;
import fr.eni.clinique.dao.JdbcTools;
import fr.eni.clinique.dao.RdvDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RdvDaoJdbcImpl implements RdvDAO {
    private static final String sqlInsertRdv = "INSERT INTO Agendas(CodeVeto, DateRdv, CodeAnimal) VALUES(?,?,?)";
    private static final String sqlSelectAllByVet = "SELECT * FROM Rdv WHERE CodeVeto=? ORDER BY DateRdv";

    @Override
    public void insert(Rdv data) {
        Connection cnx = null;
        PreparedStatement rqt = null;
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlInsertRdv);
            rqt.setInt(1, data.getCodeVeto());
            rqt.setDate(2, (Date) data.getDateRdv());
            rqt.setInt(3, data.getCodeAnimal());
            rqt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Rdv data) throws DALException {

    }

    @Override
    public void delete(Rdv data) throws DALException {

    }

    @Override
    public List<Rdv> getListRdvByVet(int codePers) throws DALException {
        Connection cnx = null;
        PreparedStatement rqt = null;
        ResultSet rs = null;
        List<Rdv> rdvList = new ArrayList<>();
        try {
            cnx = JdbcTools.getConnection();
            rqt = cnx.prepareStatement(sqlSelectAllByVet);
            rs = rqt.executeQuery();
            while (rs.next()){
                Rdv rdv = new Rdv(
                        rs.getInt("CodeVeto"),
                        rs.getDate("DateRdv"),
                        rs.getInt("CodeAnimal")
                );
                rdvList.add(rdv);
            }

        }catch (SQLException e){
            throw new DALException("La récupération des rdv a échoué.", e);
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
        return rdvList;
    }
}
