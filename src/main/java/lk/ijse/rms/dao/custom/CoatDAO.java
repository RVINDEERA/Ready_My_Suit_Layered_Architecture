package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.dto.tm.CartTm;
import java.sql.SQLException;
import java.util.List;

public interface CoatDAO {
    boolean coatDelete(String id) throws SQLException ;
    boolean saveCoat(CoatDto coatDto) throws SQLException ;
    String genarateNextMachineId() throws SQLException ;
    String splitCustomerID(String currentCoatID) ;
    public boolean updateCoat(CoatDto coatDto) throws SQLException ;
    boolean updateCoat(List<CartTm> cartTmList) throws SQLException ;
    boolean updateAvailability(String coatId) throws SQLException ;
    CoatDto searchCoat(String id) throws SQLException ;
    List<CoatDto> getAllCoat() throws SQLException ;
    List<CoatDto> loadAllItems() throws SQLException ;
}
