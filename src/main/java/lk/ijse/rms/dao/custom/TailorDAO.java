package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dto.TailorDto;

import java.sql.SQLException;
import java.util.List;

public interface TailorDAO {
    boolean saveTailor(TailorDto tailorDto) throws SQLException ;
    String genarateNextTailorId() throws SQLException ;
    String splitCustomerID(String currentTailorID) ;
    boolean updateTailor(TailorDto tailorDto) throws SQLException;
    TailorDto searchTailor(String tailorId) throws SQLException ;
    boolean deleteTailor(String tailorId) throws SQLException ;
    List<TailorDto> getAllTailor() throws SQLException ;
    List<TailorDto> getAllMachine() throws SQLException ;
}
