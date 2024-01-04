package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.UserDto;
import java.sql.SQLException;

public interface RegisterDAO {
    boolean checkDuplicate(String email,String password) throws SQLException ;
    boolean userRegister(UserDto userDto) throws SQLException ;
    boolean checkAdminPassword(String password) throws SQLException ;
}
