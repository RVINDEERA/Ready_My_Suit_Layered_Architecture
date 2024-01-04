package lk.ijse.rms.dao.custom;

import java.sql.SQLException;

public interface RentDAO {
    public boolean saveRent(String rentId, String customerId, String rentBond) throws SQLException ;
    String genarateNextRentCoatId() throws SQLException ;
    String splitRentCoatID(String currentRentCoatID) ;
}
