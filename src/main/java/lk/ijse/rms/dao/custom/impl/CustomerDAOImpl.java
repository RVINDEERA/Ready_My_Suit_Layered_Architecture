package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.CustomerDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.CustomerDto;
import lk.ijse.rms.entity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    public boolean save (Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer VALUES (?,?,?,?,?)",entity.getCustomerId(),entity.getFirstName(),entity.getLastName(),entity.getAddress(),entity.getPhoneNumber());
    }

    public Customer searchCustomer(String customerPhone ) throws SQLException, ClassNotFoundException {

        ResultSet resultSet =SQLUtil.execute("SELECT * FROM customer WHERE phoneNumber = ? ",customerPhone);
        Customer entity =null;
        if (resultSet.next()){
            entity =new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return entity;
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

    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE customer SET firstName=?,lastName=?,address=?,phoneNumber=? WHERE customerId=?",entity.getFirstName(),entity.getLastName(),entity.getAddress(),entity.getPhoneNumber(),entity.getCustomerId());

    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM customer WHERE customerId = ?",id);

    }

    public List<Customer> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");
        ArrayList<Customer> dtoList = new ArrayList<>();

        while (resultSet.next()){
            dtoList.add(
                    new Customer(
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

    public Customer search(String customerSearch) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE customerId = ?",customerSearch);
        if (rst.next()){
            Customer dto = new Customer(rst.getString("customerId"),rst.getString("firstName"),rst.getString("lastName"),rst.getString("address"),rst.getString("phoneNumber"));
            return dto;
        }else{
            return null;
        }

    }

}
