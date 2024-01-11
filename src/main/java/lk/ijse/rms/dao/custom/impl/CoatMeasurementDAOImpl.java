package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.CoatMeasurementDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.CoatMeasurementsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CoatMeasurementDAOImpl implements CoatMeasurementDAO {
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

    public boolean save(CoatMeasurementsDto coatMeasurementsDto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO coatMeasurements VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",coatMeasurementsDto.getCmId(),coatMeasurementsDto.getCustomerId(),coatMeasurementsDto.getDate(),coatMeasurementsDto.getLength(),coatMeasurementsDto.getChest(),coatMeasurementsDto.getShoulder(),coatMeasurementsDto.getSleeveLength(),coatMeasurementsDto.getCollar(),coatMeasurementsDto.getWaist(),coatMeasurementsDto.getNeck(),coatMeasurementsDto.getElbow());

    }

    public CoatMeasurementsDto search(String coatM) throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM coatMeasurements WHERE customerId = ?",coatM);
        CoatMeasurementsDto coatMeasurementsDto = null;
        if (resultSet.next()){
            coatMeasurementsDto=new CoatMeasurementsDto(
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
        return  coatMeasurementsDto;
    }

    public boolean update(CoatMeasurementsDto coatMeasurementsDto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("UPDATE coatMeasurements SET cmId=?,date=?,length=?,chest=?,shoulder=?,sleeveLength=?,collar=?,waist=?,neck=?,elbow=? WHERE customerId=?",coatMeasurementsDto.getCmId(),coatMeasurementsDto.getDate(),coatMeasurementsDto.getLength(),coatMeasurementsDto.getChest(),coatMeasurementsDto.getShoulder(),coatMeasurementsDto.getSleeveLength(),coatMeasurementsDto.getCollar(),coatMeasurementsDto.getWaist(),coatMeasurementsDto.getNeck(),coatMeasurementsDto.getElbow(),coatMeasurementsDto.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<CoatMeasurementsDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
