package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dao.SupperDAO;

import java.sql.SQLException;

public interface RentDAO extends SupperDAO {
    boolean saveRent(String rentId, String customerId, String rentBond) throws SQLException, ClassNotFoundException;

    String genarateNextRentCoatId() throws SQLException, ClassNotFoundException;

    String splitRentCoatID(String currentRentCoatID) ;
}
