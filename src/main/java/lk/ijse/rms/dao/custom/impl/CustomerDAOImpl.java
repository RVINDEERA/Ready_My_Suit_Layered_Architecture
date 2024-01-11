package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.CustomerDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    public boolean save (CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?)",customerDto.getCustomerId(),customerDto.getFirstName(),customerDto.getLastName(),customerDto.getAddress(),customerDto.getPhoneNumber());
    }

    public CustomerDto searchCustomer(String customerPhone ) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT * FROM customer WHERE phoneNumber = ? ",customerPhone);
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

    public String splitCustomerID(String currentCustomerID){
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
    public String generateNextID() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT customerId FROM customer ORDER BY customerId DESC LIMIT 1");
        if (resultSet.next()){
            return splitCustomerID(resultSet.getString(1));
        }
        return splitCustomerID(null);
    }

    public boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET firstName=?,lastName=?,address=?,phoneNumber=? WHERE customerId=?",customerDto.getFirstName(),customerDto.getLastName(),customerDto.getAddress(),customerDto.getPhoneNumber(),customerDto.getCustomerId());

    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE customerId = ?",id);

    }

    public List<CustomerDto> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
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

    public CustomerDto search(String customerSearch) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE customerId = ?",customerSearch);
        if (rst.next()){
            CustomerDto dto = new CustomerDto(rst.getString("customerId"),rst.getString("firstName"),rst.getString("lastName"),rst.getString("address"),rst.getString("phoneNumber"));
            return dto;
        }else{
            return null;
        }

    }

}
