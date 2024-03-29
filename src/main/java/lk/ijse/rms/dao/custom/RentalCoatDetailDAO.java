package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dao.SupperDAO;
import lk.ijse.rms.dto.tm.CartTm;
import java.sql.SQLException;
import java.util.List;

public interface RentalCoatDetailDAO extends SupperDAO{
    boolean saveRentCoatDetails(String rentId, List<CartTm> cartTmList) throws SQLException, ClassNotFoundException;
    boolean saveRentCoatDetails(String rentId, CartTm tm) throws SQLException, ClassNotFoundException;
}
