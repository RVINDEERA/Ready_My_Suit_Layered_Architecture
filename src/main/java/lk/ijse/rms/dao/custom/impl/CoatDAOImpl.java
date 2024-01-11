package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.CoatDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.dto.tm.CartTm;

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

    public boolean save(CoatDto coatDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO coat VALUES (?,?,?,?,?,?,?)",coatDto.getCoatId(),coatDto.getType(),coatDto.getColor(),coatDto.getAvailability(),coatDto.getDate(),coatDto.getPrice(),coatDto.getSize());
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

    public boolean update(CoatDto coatDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE coat SET type=?,color=?,avail=?,mfgDate=?,unitPrice=?,size=? WHERE coatId=?",coatDto.getType(),coatDto.getColor(),coatDto.getAvailability(),coatDto.getDate(),coatDto.getPrice(),coatDto.getSize(),coatDto.getCoatId());
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


    public CoatDto search(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM coat WHERE coatId = ?",id);

        CoatDto coatDto =null;
        if (resultSet.next()){
            coatDto =new CoatDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return coatDto;
    }

    public List<CoatDto> getAll() throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM coat");

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM coat");

        ArrayList<CoatDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new CoatDto(
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

    public List<CoatDto> loadAllItems() throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT * FROM coat WHERE avail = 'YES'";
//        PreparedStatement pstm = connection.prepareStatement(sql);


        ResultSet resultSet = SQLUtil.execute("SELECT * FROM coat WHERE avail = 'YES'");
        List<CoatDto> coatList = new ArrayList<>();

       // ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            coatList.add(new CoatDto(
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
