package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.TailorDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TailorBo extends SupperBO {
    boolean save(TailorDto tailorDto) throws SQLException, ClassNotFoundException ;
    String generateNextID() throws SQLException, ClassNotFoundException ;
    String splitCustomerID(String currentTailorID) ;
    boolean update(TailorDto tailorDto) throws SQLException, ClassNotFoundException ;
    TailorDto search(String tailorId) throws SQLException, ClassNotFoundException;
    boolean delete(String tailorId) throws SQLException, ClassNotFoundException ;
    List<TailorDto> getAll() throws SQLException, ClassNotFoundException ;
    List<TailorDto> getAllMachine() throws SQLException, ClassNotFoundException;
}
