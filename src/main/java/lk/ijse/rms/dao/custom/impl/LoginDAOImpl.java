package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.custom.LoginDAO;
import lk.ijse.rms.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAOImpl implements LoginDAO {
    public boolean userLogin(String userName, String password) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM user WHERE userName =? AND password = ?");

        pstm.setString(1,userName);
        pstm.setString(2,password);

        ResultSet resultSet = pstm.executeQuery();

        return resultSet.next();
    }
}
