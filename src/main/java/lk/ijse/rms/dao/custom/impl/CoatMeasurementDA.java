package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.CoatMeasurementDAO;
import lk.ijse.rms.entity.CoatMeasurements;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CoatMeasurementDA implements CoatMeasurementDAO {
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT cmId FROM coatMeasurements ORDER BY cmId DESC LIMIT 1");
        if (resultSet.next()){
            return splitCoatId(resultSet.getString(1));
        }
        return splitCoatId(null);
    }

    public String splitCoatId(String string) {
        if (string != null){
            String [] split = string.split("(CM)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("CM%03d", id);
        }else {
            return "CM001";
        }
    }

    public boolean save(CoatMeasurements entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO coatMeasurements VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",entity.getCmId(),entity.getCustomerId(),entity.getDate(),entity.getLength(),entity.getChest(),entity.getShoulder(),entity.getSleeveLength(),entity.getCollar(),entity.getWaist(),entity.getNeck(),entity.getElbow());

    }

    public CoatMeasurements search(String coatM) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM coatMeasurements WHERE customerId = ?",coatM);
        CoatMeasurements entityList = null;
        if (resultSet.next()){
            entityList=new CoatMeasurements(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9),
                    resultSet.getString(10),
                    resultSet.getString(11)
            );
        }
        return  entityList;
    }

    public boolean update(CoatMeasurements entity) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE coatMeasurements SET cmId=?,date=?,length=?,chest=?,shoulder=?,sleeveLength=?,collar=?,waist=?,neck=?,elbow=? WHERE customerId=?",entity.getCmId(),entity.getDate(),entity.getLength(),entity.getChest(),entity.getShoulder(),entity.getSleeveLength(),entity.getCollar(),entity.getWaist(),entity.getNeck(),entity.getElbow(),entity.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<CoatMeasurements> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
