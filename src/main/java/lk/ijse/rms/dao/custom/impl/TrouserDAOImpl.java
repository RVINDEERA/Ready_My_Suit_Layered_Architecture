package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.TrouserDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.TrouserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrouserDAOImpl implements TrouserDAO {
    public String generateNextId() throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT trmId FROM trouserMeasurements ORDER BY trmId DESC LIMIT 1";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute( "SELECT trmId FROM trouserMeasurements ORDER BY trmId DESC LIMIT 1");
        if (resultSet.next()){
            return splitrouserId(resultSet.getString(1));
        }
        return splitrouserId(null);
    }

    public String splitrouserId(String currentTrouser) {
        if (currentTrouser != null){
            String [] split = currentTrouser.split("(TM)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("TM%03d", id);
        }else {
            return "TM001";
        }
    }

    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(TrouserDto trouserDto) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("INSERT INTO trouserMeasurements VALUES (?,?,?,?,?,?,?,?,?,?)");
//
//        pstm.setString(1,trouserDto.getTrmId());
//        pstm.setString(2,trouserDto.getCustomerId());
//        pstm.setString(3,trouserDto.getDate());
//        pstm.setString(4,trouserDto.getLength());
//        pstm.setString(5,trouserDto.getWast());
//        pstm.setString(6,trouserDto.getSeat());
//        pstm.setString(7,trouserDto.getHalfSeat());
//        pstm.setString(8,trouserDto.getKnee());
//        pstm.setString(9,trouserDto.getBottm());
//        pstm.setString(10,trouserDto.getCrotch());
//
//        return pstm.executeUpdate()>0;

        return SQLUtil.execute("INSERT INTO trouserMeasurements VALUES (?,?,?,?,?,?,?,?,?,?)",trouserDto.getTrmId(),trouserDto.getCustomerId(),trouserDto.getDate(),trouserDto.getLength(),trouserDto.getWast(),trouserDto.getSeat(),trouserDto.getHalfSeat(),trouserDto.getKnee(),trouserDto.getBottm(),trouserDto.getCrotch());
    }

    public TrouserDto search(String trouserM) throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM trouserMeasurements WHERE customerId = ?");
//
//        pstm.setString(1,trouserM);
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM trouserMeasurements WHERE customerId = ?",trouserM);
        TrouserDto trouserDto = null;
        if (resultSet.next()){
            trouserDto = new TrouserDto(
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
        return trouserDto;
    }

    public boolean update(TrouserDto trouserDto) throws SQLException, ClassNotFoundException {
//        Connection connection =DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("UPDATE trouserMeasurements SET trmId=?,date=?,length=?,waist=?,seat=?,halfSeat=?,knee=?,bottom=?,crotch=? WHERE customerId=?");
//
//        pstm.setString(1,trouserDto.getTrmId());
//        pstm.setString(2,trouserDto.getDate());
//        pstm.setString(3,trouserDto.getLength());
//        pstm.setString(4,trouserDto.getWast());
//        pstm.setString(5,trouserDto.getSeat());
//        pstm.setString(6,trouserDto.getHalfSeat());
//        pstm.setString(7,trouserDto.getKnee());
//        pstm.setString(8,trouserDto.getBottm());
//        pstm.setString(9,trouserDto.getCrotch());
//        pstm.setString(10,trouserDto.getCustomerId());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("UPDATE trouserMeasurements SET trmId=?,date=?,length=?,waist=?,seat=?,halfSeat=?,knee=?,bottom=?,crotch=? WHERE customerId=?",trouserDto.getTrmId(),trouserDto.getDate(),trouserDto.getLength(),trouserDto.getWast(),trouserDto.getSeat(),trouserDto.getHalfSeat(),trouserDto.getKnee(),trouserDto.getBottm(),trouserDto.getCrotch(),trouserDto.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<TrouserDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
