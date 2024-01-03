package lk.ijse.rms.model;

import javafx.scene.control.ListCell;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {
    public boolean customerSave(CustomerDto customerDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?)");

        pstm.setString(1,customerDto.getCustomerId());
        pstm.setString(2,customerDto.getFirstName());
        pstm.setString(3,customerDto.getLastName());
        pstm.setString(4,customerDto.getAddress());
        pstm.setString(5,customerDto.getPhoneNumber());

        return pstm.executeUpdate() > 0;
    }

    public CustomerDto searchCustomer(String customerPhone ) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer WHERE phoneNumber = ? ");

        pstm.setString(1, customerPhone);


        ResultSet resultSet = pstm.executeQuery();

        CustomerDto customerDto =null;
        if (resultSet.next()){
            customerDto =new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return customerDto;
    }

    private String splitCustomerID(String currentCustomerID){
        if (currentCustomerID != null){
            String [] split = currentCustomerID.split("(CUS)");

            int id = Integer.parseInt(split[1]);
            id++;
            //return String.format("CUS%03d",id);
            return String.format("CUS%03d",id);
        }else {
            return "CUS001";
        }
    }
    public String genarateNextCustomerId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT customerId FROM customer ORDER BY customerId DESC LIMIT 1";
        PreparedStatement ptsm = connection.prepareStatement(sql);

        ResultSet resultSet = ptsm.executeQuery();
        if (resultSet.next()){
            return splitCustomerID(resultSet.getString(1));
        }
        return splitCustomerID(null);
    }

    public boolean updateCustomer(CustomerDto customerDto) throws SQLException {
        Connection connection =DbConnection.getInstance().getConnection();;
        PreparedStatement pstm = connection.prepareStatement("UPDATE customer SET firstName=?,lastName=?,address=?,phoneNumber=? WHERE customerId=?");

        pstm.setString(1,customerDto.getFirstName());
        pstm.setString(2,customerDto.getLastName());
        pstm.setString(3,customerDto.getAddress());
        pstm.setString(4,customerDto.getPhoneNumber());
        pstm.setString(5,customerDto.getCustomerId());

        return pstm.executeUpdate() > 0;

    }

    public boolean customerDelete(String id) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("DELETE FROM customer WHERE customerId = ?");

        pstm.setString(1,id);
        return pstm.executeUpdate() > 0;

    }

    public List<CustomerDto> getAllCustomer() throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM customer");

        ResultSet resultSet = pstm.executeQuery();

        ArrayList<CustomerDto> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new CustomerDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5)
                    )
            );
        }
        return dtoList;
    }

    public CustomerDto searchCustomerId(String customerSearch) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer WHERE customerId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, customerSearch);
        ResultSet resultSet = pstm.executeQuery();

        CustomerDto customerDto =null;
        if (resultSet.next()){
            customerDto =new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return customerDto;
    }
}
