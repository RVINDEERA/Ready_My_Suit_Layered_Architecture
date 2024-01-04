package lk.ijse.rms.dao;

import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.PlaceOrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderFormModel {

    private  OrderModel orderModel = new OrderModel();
    private ItemModel itemModel = new ItemModel();
    private OrderItemModel orderItemModel= new OrderItemModel();

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

            boolean isOrderSaved = orderModel.saveOrder(orderId,date,customerId,tailorId,fullAmount,advance,balance,status,completeDate);
            if (isOrderSaved){
                boolean isUpdated = itemModel.updateItem(placeOrderDto.getOrderCartTmList());
                if (isUpdated){
                    boolean isOrderItemSaved = orderItemModel.saveOrderItem(placeOrderDto.getOrderId(),placeOrderDto.getOrderCartTmList());
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

        } catch (SQLException e) {
            if (connection !=null)connection.rollback();
            e.printStackTrace();
        }finally {
            if (connection !=null) connection.setAutoCommit(true);
        }
        return false;
    }
}
