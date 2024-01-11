package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dto.TailorDto;
import lk.ijse.rms.entity.Tailor;

import java.sql.SQLException;
import java.util.List;

public interface TailorDAO extends CrudDAO<Tailor> {
   String splitCustomerID(String currentTailorID) ;
   // boolean updateTailor(TailorDto tailorDto) throws SQLException, ClassNotFoundException;
    //TailorDto searchTailor(String tailorId) throws SQLException, ClassNotFoundException;
    //boolean deleteTailor(String tailorId) throws SQLException, ClassNotFoundException;
    //List<TailorDto> getAllTailor() throws SQLException, ClassNotFoundException;
    List<Tailor> getAllMachine() throws SQLException, ClassNotFoundException;
}
