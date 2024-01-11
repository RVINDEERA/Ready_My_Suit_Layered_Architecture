package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.CoatDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.dto.tm.CartTm;
import lk.ijse.rms.entity.Coat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoatDAOImpl implements CoatDAO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM coat WHERE coatId = ?",id);
    }

    public boolean save(Coat entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO coat VALUES (?,?,?,?,?,?,?)",entity.getCoatId(),entity.getType(),entity.getColor(),entity.getAvailability(),entity.getDate(),entity.getPrice(),entity.getSize());
    }

    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute( "SELECT coatId FROM coat ORDER BY coatId DESC LIMIT 1");
        if (resultSet.next()){
            return splitCustomerID(resultSet.getString(1));
        }
        return splitCustomerID(null);
    }

    public String splitCustomerID(String currentCoatID) {
        if (currentCoatID != null){
            String [] split = currentCoatID.split("(COT)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("COT%03d", id);
        }else {
            return "COT001";
        }
    }

    public boolean update(Coat entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE coat SET type=?,color=?,avail=?,mfgDate=?,unitPrice=?,size=? WHERE coatId=?",entity.getType(),entity.getColor(),entity.getAvailability(),entity.getDate(),entity.getPrice(),entity.getSize(),entity.getCoatId());
    }

    public boolean updateCoat(List<CartTm>cartTmList) throws SQLException, ClassNotFoundException {
        for(CartTm tm : cartTmList) {
            System.out.println("Coat: " + tm);
            if(!updateAvailability(tm.getCoatId())) {
                return false;
            }
        }
        return true;
    }

    public boolean updateAvailability(String coatId) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "UPDATE coat SET avail = 'NO' WHERE coatId = ?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setString(1, coatId);
//
//
//
//        return pstm.executeUpdate() > 0; //false
        return SQLUtil.execute("UPDATE coat SET avail = 'NO' WHERE coatId = ?",coatId);
    }


    public Coat search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM coat WHERE coatId = ?",id);

        Coat entity =null;
        if (resultSet.next()){
            entity =new Coat(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return entity;
    }

    public List<Coat> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM coat");

        ArrayList<Coat> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new Coat(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7)
                    )
            );
        }
        return dtoList;
    }

    public List<Coat> loadAllItems() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM coat WHERE avail = 'YES'");
        List<Coat> coatList = new ArrayList<>();

        while (resultSet.next()) {
            coatList.add(new Coat(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return coatList;
    }
}
