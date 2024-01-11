package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.TrouserDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.TrouserDto;
import lk.ijse.rms.entity.Trouser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TrouserDAOImpl implements TrouserDAO {
    public String generateNextID() throws SQLException, ClassNotFoundException {
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


    public boolean save(Trouser trouserDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO trouserMeasurements VALUES (?,?,?,?,?,?,?,?,?,?)",trouserDto.getTrmId(),trouserDto.getCustomerId(),trouserDto.getDate(),trouserDto.getLength(),trouserDto.getWast(),trouserDto.getSeat(),trouserDto.getHalfSeat(),trouserDto.getKnee(),trouserDto.getBottm(),trouserDto.getCrotch());
    }

    public Trouser search(String trouserM) throws SQLException, ClassNotFoundException {
//        Connection connection=DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM trouserMeasurements WHERE customerId = ?");
//
//        pstm.setString(1,trouserM);
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM trouserMeasurements WHERE customerId = ?",trouserM);
        Trouser trouserDto = null;
        if (resultSet.next()){
            trouserDto = new Trouser(
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

    public boolean update(Trouser trouserDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE trouserMeasurements SET trmId=?,date=?,length=?,waist=?,seat=?,halfSeat=?,knee=?,bottom=?,crotch=? WHERE customerId=?",trouserDto.getTrmId(),trouserDto.getDate(),trouserDto.getLength(),trouserDto.getWast(),trouserDto.getSeat(),trouserDto.getHalfSeat(),trouserDto.getKnee(),trouserDto.getBottm(),trouserDto.getCrotch(),trouserDto.getCustomerId());
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<Trouser> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }
}
