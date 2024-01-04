package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {
    boolean customerSave(CustomerDto customerDto) throws SQLException;
    CustomerDto searchCustomer(String customerPhone ) throws SQLException;
    String splitCustomerID(String currentCustomerID);
    String genarateNextCustomerId() throws SQLException ;
    boolean updateCustomer(CustomerDto customerDto) throws SQLException ;
    boolean customerDelete(String id) throws SQLException ;
    List<CustomerDto> getAllCustomer() throws SQLException ;
    CustomerDto searchCustomerId(String customerSearch) throws SQLException ;

    }
