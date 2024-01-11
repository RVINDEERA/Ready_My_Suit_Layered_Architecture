package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.ShirtMeasurementDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.ShirtDto;
import lk.ijse.rms.entity.Shirt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShirtMeasurementDAOImpl implements ShirtMeasurementDAO {
    public boolean save(Shirt entity) throws SQLException, ClassNotFoundException {
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

        return SQLUtil.execute("INSERT INTO shirtMeasurements VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",entity.getSmId(),entity.getCustomerId(),entity.getDate(),entity.getLength(),entity.getChest(),entity.getShoulder(),entity.getSleeveLength(),entity.getCollar(),entity.getCuff(),entity.getWaist());

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

    public Shirt search(String shirtM) throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM shirtMeasurements WHERE customerId = ?");
//
//        pstm.setString(1,shirtM);
//
//        ResultSet resultSet = pstm.executeQuery();
//
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM shirtMeasurements WHERE customerId = ?",shirtM);
        Shirt entitylist = null;
        if (resultSet.next()){
            entitylist=new Shirt(
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
        return entitylist;
    }

    public boolean update(Shirt entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE shirtMeasurements SET smId=?,date=?,length=?,chest=?,shoulder=?,sleeveLength=?,collar=?,cuff=?,waist=? WHERE customerId=?",entity.getSmId(),entity.getDate(),entity.getLength(),entity.getChest(),entity.getShoulder(),entity.getSleeveLength(),entity.getCollar(),entity.getCuff(),entity.getWaist(),entity.getCustomerId());


    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Shirt> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

}
