package lk.ijse.rms.dao.custom;

import java.sql.SQLException;

public interface LoginDAO {
    boolean userLogin(String userName, String password) throws SQLException ;

}
