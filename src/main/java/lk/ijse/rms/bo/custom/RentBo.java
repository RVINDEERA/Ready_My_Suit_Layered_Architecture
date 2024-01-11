package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RentBo extends SupperBO {
    public boolean saveRent(String rentId, String customerId, String rentBond) throws SQLException, ClassNotFoundException ;

    public String genarateNextRentCoatId() throws SQLException, ClassNotFoundException ;

    public String splitRentCoatID(String currentRentCoatID) ;
}
