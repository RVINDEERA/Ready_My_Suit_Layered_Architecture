package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.ShirtMeasurementDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.ShirtDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShirtMeasurementDAOImpl implements ShirtMeasurementDAO {
    public boolean save(ShirtDto shirtDto) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        String sql = "INSERT INTO shirtMeasurements VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setString(1,shirtDto.getSmId());
//        pstm.setString(2,shirtDto.getCustomerId());
//        pstm.setString(3,shirtDto.getDate());
//        pstm.setString(4,shirtDto.getLength());
//        pstm.setString(5,shirtDto.getChest());
//        pstm.setString(6,shirtDto.getShoulder());
//        pstm.setString(7,shirtDto.getSleeveLength());
//        pstm.setString(8,shirtDto.getCollar());
//        pstm.setString(9,shirtDto.getCuff());
//        pstm.setString(10,shirtDto.getWaist());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("INSERT INTO shirtMeasurements VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",shirtDto.getSmId(),shirtDto.getCustomerId(),shirtDto.getDate(),shirtDto.getLength(),shirtDto.getChest(),shirtDto.getShoulder(),shirtDto.getSleeveLength(),shirtDto.getCollar(),shirtDto.getCuff(),shirtDto.getWaist());

    }

    public String generateNextID() throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT smId FROM shirtMeasurements ORDER BY smId DESC LIMIT 1";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT smId FROM shirtMeasurements ORDER BY smId DESC LIMIT 1");
        if (resultSet.next()){
            return splitShirtID(resultSet.getString(1));
        }
        return splitShirtID(null);
    }

    public String splitShirtID(String currentShirtID) {
        if (currentShirtID != null){
            String [] split = currentShirtID.split("(SM)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("SM%03d", id);
        }else {
            return "SM001";
        }
    }

    public ShirtDto search(String shirtM) throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM shirtMeasurements WHERE customerId = ?");
//
//        pstm.setString(1,shirtM);
//
//        ResultSet resultSet = pstm.executeQuery();
//
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM shirtMeasurements WHERE customerId = ?",shirtM);
        ShirtDto shirtDto = null;
        if (resultSet.next()){
            shirtDto=new ShirtDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10)
            );
        }
        return shirtDto;
    }

    public boolean update(ShirtDto shirtDto) throws SQLException, ClassNotFoundException {
//        Connection connection =DbConnection.getInstance().getConnection();;
//        PreparedStatement pstm = connection.prepareStatement("UPDATE shirtMeasurements SET smId=?,date=?,length=?,chest=?,shoulder=?,sleeveLength=?,collar=?,cuff=?,waist=? WHERE customerId=?");
//
//        pstm.setString(1,shirtDto.getSmId());
//        pstm.setString(2,shirtDto.getDate());
//        pstm.setString(3,shirtDto.getLength());
//        pstm.setString(4,shirtDto.getChest());
//        pstm.setString(5,shirtDto.getShoulder());
//        pstm.setString(6,shirtDto.getSleeveLength());
//        pstm.setString(7,shirtDto.getCollar());
//        pstm.setString(8,shirtDto.getCuff());
//        pstm.setString(9,shirtDto.getWaist());
//        pstm.setString(10,shirtDto.getCustomerId());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("UPDATE shirtMeasurements SET smId=?,date=?,length=?,chest=?,shoulder=?,sleeveLength=?,collar=?,cuff=?,waist=? WHERE customerId=?",shirtDto.getSmId(),shirtDto.getDate(),shirtDto.getLength(),shirtDto.getChest(),shirtDto.getShoulder(),shirtDto.getSleeveLength(),shirtDto.getCollar(),shirtDto.getCuff(),shirtDto.getWaist(),shirtDto.getCustomerId());


    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<ShirtDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

}
