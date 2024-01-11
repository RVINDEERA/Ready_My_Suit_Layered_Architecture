package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dao.SupperDAO;

import java.sql.SQLException;

public interface LoginDAO extends SupperDAO {
    boolean userLogin(String userName, String password) throws SQLException, ClassNotFoundException;

}
