package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO <CustomerDto>{
    CustomerDto searchCustomer(String customerPhone ) throws SQLException, ClassNotFoundException;
    String splitCustomerID(String currentCustomerID);

    }
