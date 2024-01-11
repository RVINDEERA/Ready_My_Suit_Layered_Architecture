package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.UserDto;
import java.sql.SQLException;

public interface RegisterDAO extends CrudDAO<UserDto>{
    boolean checkDuplicate(String email,String password) throws SQLException, ClassNotFoundException;
    //boolean userRegister(UserDto userDto) throws SQLException, ClassNotFoundException;
   // boolean checkAdminPassword(String password) throws SQLException, ClassNotFoundException;
}
