package lk.ijse.rms.dao;

import lk.ijse.rms.dao.custom.CoatMeasurementDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.CoatMeasurementsDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoatMeasurementDAOImpl implements CoatMeasurementDAO {
    public String generateNextCoatId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT cmId FROM coatMeasurements ORDER BY cmId DESC LIMIT 1";
        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();
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

    public boolean coatMeasurementSave(CoatMeasurementsDto coatMeasurementsDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "INSERT INTO coatMeasurements VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,coatMeasurementsDto.getCmId());
        pstm.setString(2,coatMeasurementsDto.getCustomerId());
        pstm.setString(3,coatMeasurementsDto.getDate());
        pstm.setString(4,coatMeasurementsDto.getLength());
        pstm.setString(5,coatMeasurementsDto.getChest());
        pstm.setString(6,coatMeasurementsDto.getShoulder());
        pstm.setString(7,coatMeasurementsDto.getSleeveLength());
        pstm.setString(8,coatMeasurementsDto.getCollar());
        pstm.setString(9,coatMeasurementsDto.getWaist());
        pstm.setString(10,coatMeasurementsDto.getNeck());
        pstm.setString(11,coatMeasurementsDto.getElbow());

        return pstm.executeUpdate() > 0;

    }

    public CoatMeasurementsDto searchMeasurements(String coatM) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM coatMeasurements WHERE customerId = ?");

        pstm.setString(1,coatM);
        ResultSet resultSet = pstm.executeQuery();
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

    public boolean updateCoat(CoatMeasurementsDto coatMeasurementsDto) throws SQLException {
        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE coatMeasurements SET cmId=?,date=?,length=?,chest=?,shoulder=?,sleeveLength=?,collar=?,waist=?,neck=?,elbow=? WHERE customerId=?");

        pstm.setString(1,coatMeasurementsDto.getCmId());
        pstm.setString(2,coatMeasurementsDto.getDate());
        pstm.setString(3,coatMeasurementsDto.getLength());
        pstm.setString(4,coatMeasurementsDto.getChest());
        pstm.setString(5,coatMeasurementsDto.getShoulder());
        pstm.setString(6,coatMeasurementsDto.getSleeveLength());
        pstm.setString(7,coatMeasurementsDto.getCollar());
        pstm.setString(8,coatMeasurementsDto.getWaist());
        pstm.setString(9,coatMeasurementsDto.getNeck());
        pstm.setString(10,coatMeasurementsDto.getElbow());
        pstm.setString(11,coatMeasurementsDto.getCustomerId());

        return pstm.executeUpdate() > 0;
    }
}
