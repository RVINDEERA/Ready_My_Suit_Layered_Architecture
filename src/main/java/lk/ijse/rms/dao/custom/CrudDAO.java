package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dao.SupperDAO;
import lk.ijse.rms.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO <T> extends SupperDAO {
    String generateNextID() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    List<T> getAll() throws SQLException, ClassNotFoundException;
    T search(String id) throws SQLException, ClassNotFoundException;

}
