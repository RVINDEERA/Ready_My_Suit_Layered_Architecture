package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.OrderDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface OrderBo extends SupperBO {
    public  String generateNextID() throws SQLException, ClassNotFoundException ;
    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException;
    public boolean update(OrderDto dto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException ;
    public  String splitOrderId(String string);
    public boolean saveOrder(String orderId, String date, String customerId, String tailorId, double fullAmount, double advance, double balance, String status, String completeDate) throws SQLException, ClassNotFoundException ;
    public List<OrderDto> getAll() throws SQLException, ClassNotFoundException ;
    public boolean updateOrderStatus(String id,String status) throws SQLException, ClassNotFoundException ;
    public OrderDto search(String id) throws SQLException, ClassNotFoundException;
}
