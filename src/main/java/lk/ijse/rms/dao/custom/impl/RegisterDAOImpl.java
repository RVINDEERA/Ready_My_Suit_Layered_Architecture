package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.RegisterDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RegisterDAOImpl implements RegisterDAO {
    public boolean checkDuplicate(String email,String password) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE email = ? AND password =?");
//
//        ResultSet resultSet = pstm.executeQuery();

//            pstm.setString(1,email);
//            pstm.setString(2,password);

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE email = ? AND password =?");
        return resultSet.next();

    }


    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(UserDto userDto) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("INSERT INTO  user VALUES (?,?,?)");
//
//        pstm.setString(1,userDto.getEmail());
//        pstm.setString(2,userDto.getUserName());
//        pstm.setString(3,userDto.getPassword());
//
//        return pstm.executeUpdate() > 0 ;
        return SQLUtil.execute("INSERT INTO  user VALUES (?,?,?)",userDto.getEmail(),userDto.getUserName(),userDto.getPassword());

    }

    @Override
    public boolean update(UserDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<UserDto> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public UserDto search(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

//    public boolean checkAdminPassword(String password) throws SQLException, ClassNotFoundException {
//
//        ResultSet resultSet = SQLUtil.execute("SELECT * FROM user WHERE password = ?",password);
//        return resultSet.next();
// }
}
