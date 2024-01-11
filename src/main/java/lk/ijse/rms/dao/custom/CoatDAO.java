package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.dto.tm.CartTm;
import lk.ijse.rms.entity.Coat;

import java.sql.SQLException;
import java.util.List;

public interface CoatDAO extends CrudDAO <Coat> {
    String splitCustomerID(String currentCoatID) ;
    boolean updateCoat(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException;
    boolean updateAvailability(String coatId) throws SQLException, ClassNotFoundException;
    List<Coat> loadAllItems() throws SQLException, ClassNotFoundException;
}
