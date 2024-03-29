package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dao.SupperDAO;
import lk.ijse.rms.dto.tm.OrderCartTm;

import java.sql.SQLException;
import java.util.List;

public interface OrderItemDAO  extends SupperDAO {
    boolean saveOrderItem(String orderId, List<OrderCartTm> orderCartTmList) throws SQLException , ClassNotFoundException ;

    boolean saveOrderItem(String orderId, OrderCartTm tm) throws SQLException,ClassNotFoundException ;
}
