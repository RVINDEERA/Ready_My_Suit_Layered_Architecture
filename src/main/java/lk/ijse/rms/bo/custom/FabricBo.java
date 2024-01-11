package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.FabricDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FabricBo extends SupperBO {
    String generateNextID() throws SQLException, ClassNotFoundException;
    boolean save(FabricDto fabricDto) throws SQLException, ClassNotFoundException;
     boolean delete(String id) throws SQLException, ClassNotFoundException;
     boolean update(FabricDto fabricDto) throws SQLException, ClassNotFoundException;
     FabricDto search(String id) throws SQLException, ClassNotFoundException;
    List<FabricDto> getAll() throws SQLException, ClassNotFoundException;
}
