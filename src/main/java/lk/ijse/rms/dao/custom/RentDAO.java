package lk.ijse.rms.dao.custom;

import java.sql.SQLException;

public interface RentDAO {
    boolean saveRent(String rentId, String customerId, String rentBond) throws SQLException, ClassNotFoundException;
    String genarateNextRentCoatId() throws SQLException, ClassNotFoundException;
    String splitRentCoatID(String currentRentCoatID) ;
}
