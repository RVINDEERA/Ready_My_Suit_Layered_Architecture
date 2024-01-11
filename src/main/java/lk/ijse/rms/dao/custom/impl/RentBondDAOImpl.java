package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.RentBondDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.RentalBondDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentBondDAOImpl implements RentBondDAO {

    public List<RentalBondDto> loadAllBond() throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT * FROM rentalbond";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM rentalbond");

        List<RentalBondDto> bondList = new ArrayList<>();

        while (resultSet.next()) {
            bondList.add(new RentalBondDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            ));
        }

        return bondList;
    }
}
