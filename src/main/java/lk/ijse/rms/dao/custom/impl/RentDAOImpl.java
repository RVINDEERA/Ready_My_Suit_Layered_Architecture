package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.RentDAO;
import lk.ijse.rms.db.DbConnection;

import java.sql.*;

public class RentDAOImpl implements RentDAO {

    public boolean saveRent(String rentId, String customerId, String rentBond) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO rent VALUES(?, ?, ?)",rentId,customerId,rentBond);
    }


    public String genarateNextRentCoatId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT rentId FROM rent ORDER BY rentId DESC LIMIT 1");
        if (resultSet.next()){
            return splitRentCoatID(resultSet.getString(1));
        }
        return splitRentCoatID(null);
    }

    public String splitRentCoatID(String currentRentCoatID) {
        if (currentRentCoatID != null){
            String [] split = currentRentCoatID.split("(RNT)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("RNT%03d", id);
        }else {
            return "RNT001";
        }
    }
}

