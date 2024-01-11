package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dto.TailorDto;

import java.sql.SQLException;
import java.util.List;

public interface TailorDAO extends CrudDAO<TailorDto> {
    //boolean saveTailor(TailorDto tailorDto) throws SQLException, ClassNotFoundException;
    //String genarateNextTailorId() throws SQLException, ClassNotFoundException;
    String splitCustomerID(String currentTailorID) ;
   // boolean updateTailor(TailorDto tailorDto) throws SQLException, ClassNotFoundException;
    //TailorDto searchTailor(String tailorId) throws SQLException, ClassNotFoundException;
    //boolean deleteTailor(String tailorId) throws SQLException, ClassNotFoundException;
    //List<TailorDto> getAllTailor() throws SQLException, ClassNotFoundException;
    List<TailorDto> getAllMachine() throws SQLException, ClassNotFoundException;
}
