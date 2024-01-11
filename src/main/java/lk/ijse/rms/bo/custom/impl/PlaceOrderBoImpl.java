package lk.ijse.rms.bo.custom.impl;

import lk.ijse.rms.bo.custom.PlaceOrderBo;
import lk.ijse.rms.dao.DAOFactory;
import lk.ijse.rms.dao.custom.ItemDAO;
import lk.ijse.rms.dao.custom.OrderDAO;
import lk.ijse.rms.dao.custom.OrderItemDAO;
import lk.ijse.rms.dao.custom.impl.ItemDAOImpl;
import lk.ijse.rms.dao.custom.impl.OrderDAOImpl;
import lk.ijse.rms.dao.custom.impl.OrderItemDAOImpl;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.PlaceOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderBoImpl implements PlaceOrderBo {

    OrderDAO orderDAOImpl = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    ItemDAO itemDAOImpl = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    OrderItemDAO orderItemDAOImpl = (OrderItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAIL);

    public boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException {

        String orderId = placeOrderDto.getOrderId();
        String date =placeOrderDto.getDate();
        String customerId =placeOrderDto.getCustomerId();
        String tailorId = placeOrderDto.getTailorId();
        double fullAmount = placeOrderDto.getFullAmount();
        double advance = placeOrderDto.getAdvance();
        double balance = placeOrderDto.getBalance();
        String status = placeOrderDto.getStatus();
        String completeDate = placeOrderDto.getCompleteDate();

        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean isOrderSaved = orderDAOImpl.saveOrder(orderId,date,customerId,tailorId,fullAmount,advance,balance,status,completeDate);
            if (isOrderSaved){
                boolean isUpdated = itemDAOImpl.updateItem(placeOrderDto.getOrderCartTmList());
                if (isUpdated){
                    boolean isOrderItemSaved = orderItemDAOImpl.saveOrderItem(placeOrderDto.getOrderId(),placeOrderDto.getOrderCartTmList());
                    if (isOrderItemSaved){
                        connection.commit();
                        return  true;
                    }else{
                        connection.rollback();
                    }
                }else {
                    connection.rollback();
                }
            }else {
                connection.rollback();
            }

        } catch (SQLException | ClassNotFoundException e) {
            if (connection !=null)connection.rollback();
            e.printStackTrace();
        }finally {
            if (connection !=null) connection.setAutoCommit(true);
        }
        return false;
    }
}
