package lk.ijse.rms.dao;

import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.RentalBondDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RentBondModel {

    public List<RentalBondDto> loadAllBond() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM rentalbond";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<RentalBondDto> bondList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
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
