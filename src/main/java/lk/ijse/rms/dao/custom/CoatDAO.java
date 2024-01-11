package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.dto.tm.CartTm;
import java.sql.SQLException;
import java.util.List;

public interface CoatDAO extends CrudDAO <CoatDto>{
   // boolean coatDelete(String id) throws SQLException, ClassNotFoundException;
  //  boolean saveCoat(CoatDto coatDto) throws SQLException, ClassNotFoundException;
   // String genarateNextMachineId() throws SQLException, ClassNotFoundException;
    String splitCustomerID(String currentCoatID) ;
  //  public boolean updateCoat(CoatDto coatDto) throws SQLException, ClassNotFoundException;
    boolean updateCoat(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException;
    boolean updateAvailability(String coatId) throws SQLException, ClassNotFoundException;
   // CoatDto searchCoat(String id) throws SQLException, ClassNotFoundException;
    //List<CoatDto> getAllCoat() throws SQLException, ClassNotFoundException;
    List<CoatDto> loadAllItems() throws SQLException, ClassNotFoundException;
}
