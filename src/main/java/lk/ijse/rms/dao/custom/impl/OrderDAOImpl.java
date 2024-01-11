package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.OrderDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.OrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    public  String generateNextID() throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1";
//        PreparedStatement ptsm = connection.prepareStatement(sql);
//
//        ResultSet resultSet = ptsm.executeQuery();

        ResultSet resultSet = SQLUtil.execute("SELECT orderId FROM orders ORDER BY orderId DESC LIMIT 1");
        if (resultSet.next()){
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);

    }

    @Override
    public boolean save(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(OrderDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    public  String splitOrderId(String string) {
        if (string != null){
            String [] split = string.split("(O)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("O%03d", id);
        }else {
            return "O001";
        }
    }

    public boolean saveOrder(String orderId, String date, String customerId, String tailorId, double fullAmount, double advance, double balance, String status, String completeDate) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        PreparedStatement pstm =connection.prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?,?,?,?,?)");
//
//        pstm.setString(1,orderId);
//        pstm.setString(2,date);
//        pstm.setString(3,customerId);
//        pstm.setString(4,tailorId);
//        pstm.setDouble(5,fullAmount);
//        pstm.setDouble(6,advance);
//        pstm.setDouble(7,balance);
//        pstm.setString(8,status);
//        pstm.setString(9,completeDate);
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("INSERT INTO orders VALUES (?,?,?,?,?,?,?,?,?)",orderId,date,customerId,tailorId,fullAmount,advance,balance,status,completeDate);

    }


    public List<OrderDto> getAll() throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "SELECT * FROM orders";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        ResultSet resultSet = pstm.executeQuery();


        ResultSet resultSet = SQLUtil.execute("SELECT * FROM orders");

        ArrayList<OrderDto> dtoList = new ArrayList<>();

        while(resultSet.next()) {
            dtoList.add(
                    new OrderDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getDouble(5),
                            resultSet.getDouble(6),
                            resultSet.getDouble(7),
                            resultSet.getString(8),
                            resultSet.getString(9)
                    )
            );
        }
        return dtoList;

    }

    public boolean updateOrderStatus(String id,String status) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        String sql = "UPDATE orders SET  status = ? WHERE orderId = ?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1,status);
//        pstm.setString(2,id);
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("UPDATE orders SET  status = ? WHERE orderId = ?",status,id);
    }

    public OrderDto search(String id) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection ();
//
//        String sql = "SELECT * FROM orders WHERE orderId = ?";
//        PreparedStatement pstm = connection.prepareStatement(sql);
//        pstm.setString(1, id);
//
//        ResultSet resultSet = pstm.executeQuery();

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM orders WHERE orderId = ?",id);

        OrderDto dto = null;
        if(resultSet.next()) {
            String orderId = resultSet.getString(1);
            String date = resultSet.getString(2);
            String customerId = resultSet.getString(3);
            String tailorId = resultSet.getString(4);
            double fullAmount = Double.parseDouble(resultSet.getString(5));
            double advance = Double.parseDouble(resultSet.getString(6));
            double balance = Double.parseDouble(resultSet.getString(7));
            String status = resultSet.getString(8);
            String completeDate = resultSet.getString(9);


            dto = new OrderDto(orderId,date,customerId,tailorId,fullAmount,advance,balance,status,completeDate);
        }
        return dto;
    }
}
