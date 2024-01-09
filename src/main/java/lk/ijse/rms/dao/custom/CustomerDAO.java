package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    boolean customerSave(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    CustomerDto searchCustomer(String customerPhone ) throws SQLException, ClassNotFoundException;
    String splitCustomerID(String currentCustomerID);
    String genarateNextCustomerId() throws SQLException, ClassNotFoundException;
    boolean updateCustomer(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    boolean customerDelete(String id) throws SQLException, ClassNotFoundException;
    List<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;
    CustomerDto searchCustomerId(String customerSearch) throws SQLException, ClassNotFoundException;

    }
