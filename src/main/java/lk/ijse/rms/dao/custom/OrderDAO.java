package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.OrderDto;
import lk.ijse.rms.entity.Order;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends CrudDAO<Order>{
    String splitOrderId(String string) ;
    boolean saveOrder(String orderId, String date, String customerId, String tailorId, double fullAmount, double advance, double balance, String status, String completeDate) throws SQLException, ClassNotFoundException;
    boolean updateOrderStatus(String id,String status) throws SQLException, ClassNotFoundException;
}
