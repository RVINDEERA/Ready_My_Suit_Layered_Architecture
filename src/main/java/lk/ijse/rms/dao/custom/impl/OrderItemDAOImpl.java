package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.OrderItemDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.tm.OrderCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderItemDAOImpl implements OrderItemDAO {
    public boolean saveOrderItem(String orderId, List<OrderCartTm> orderCartTmList) throws SQLException, ClassNotFoundException {
        for(OrderCartTm tm : orderCartTmList) {
            if(!saveOrderItem(orderId, tm)) {
                return false;
            }
        }
        return true;

    }

    public boolean saveOrderItem(String orderId, OrderCartTm tm) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "INSERT INTO orderItem VALUES(?, ?, ?, ?)";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//
//        pstm.setString(1,orderId);
//        pstm.setString(2,tm.getItemId());
//        pstm.setInt(3,tm.getQty());
//        pstm.setString(4,tm.getNote());
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("INSERT INTO orderItem VALUES(?, ?, ?, ?)",orderId,tm.getItemId(),tm.getQty(),tm.getNote());
    }
}
