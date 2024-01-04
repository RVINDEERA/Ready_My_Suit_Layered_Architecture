package lk.ijse.rms.dao;

import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RentalCoatDetailModel {
    public boolean saveRentCoatDetails(String rentId, List<CartTm> cartTmList) throws SQLException {
        for(CartTm tm : cartTmList) {
            if(!saveRentCoatDetails(rentId, tm)) {
                return false;
            }
        }
        return true;
    }

    private boolean saveRentCoatDetails(String rentId, CartTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO rentalCoatDetail VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,tm.getCoatId());
        pstm.setString(2, rentId);
        pstm.setString(3, tm.getPrice());
        pstm.setString(4, tm.getRentDate());
        pstm.setString(5, tm.getReturnDate());

        return pstm.executeUpdate() > 0;
    }
}
