package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.RentalCoatDetailDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RentalCoatDetailDAOImpl implements RentalCoatDetailDAO {
    public boolean saveRentCoatDetails(String rentId, List<CartTm> cartTmList) throws SQLException, ClassNotFoundException {
        for(CartTm tm : cartTmList) {
            if(!saveRentCoatDetails(rentId, tm)) {
                return false;
            }
        }
        return true;
    }

     public boolean saveRentCoatDetails(String rentId, CartTm tm) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "INSERT INTO rentalCoatDetail VALUES(?, ?, ?, ?, ?)";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setString(1,tm.getCoatId());
//        pstm.setString(2, rentId);
//        pstm.setString(3, tm.getPrice());
//        pstm.setString(4, tm.getRentDate());
//        pstm.setString(5, tm.getReturnDate());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("INSERT INTO rentalCoatDetail VALUES(?, ?, ?, ?, ?)",tm.getCoatId(),rentId,tm.getPrice(),tm.getRentDate(),tm.getReturnDate());
    }
}
