package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.CustomerDto;
import lk.ijse.rms.entity.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO <Customer>{
    Customer searchCustomer(String customerPhone ) throws SQLException, ClassNotFoundException;
    String splitCustomerID(String currentCustomerID);

    }
