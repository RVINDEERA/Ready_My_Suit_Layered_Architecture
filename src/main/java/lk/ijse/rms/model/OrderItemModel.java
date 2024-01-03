package lk.ijse.rms.model;

import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.tm.CartTm;
import lk.ijse.rms.dto.tm.OrderCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemModel {
    public boolean saveOrderItem(String orderId, List<OrderCartTm> orderCartTmList) throws SQLException {
        for(OrderCartTm tm : orderCartTmList) {
            if(!saveOrderItem(orderId, tm)) {
                return false;
            }
        }
        return true;

    }

    private boolean saveOrderItem(String orderId, OrderCartTm tm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO orderItem VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,orderId);
        pstm.setString(2,tm.getItemId());
        pstm.setInt(3,tm.getQty());
        pstm.setString(4,tm.getNote());

        return pstm.executeUpdate() > 0;
    }
}
