package lk.ijse.rms.bo.custom.impl;

import lk.ijse.rms.bo.custom.CustomerBo;
import lk.ijse.rms.dao.DAOFactory;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.CustomerDAO;
import lk.ijse.rms.dto.CoatMeasurementsDto;
import lk.ijse.rms.dto.CustomerDto;
import lk.ijse.rms.entity.CoatMeasurements;
import lk.ijse.rms.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    public boolean save (CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDAO.save(new Customer(customerDto.getCustomerId(),customerDto.getFirstName(),customerDto.getLastName(),customerDto.getAddress(),customerDto.getPhoneNumber()));
    }

    public CustomerDto searchCustomer(String customerPhone ) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(customerPhone);
        if (customer != null) {
            return new CustomerDto(customer.getCustomerId(),customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getPhoneNumber());
        } else {
            return null;
        }
    }

    public String generateNextID() throws SQLException, ClassNotFoundException {
        return customerDAO.generateNextID();
    }

    public boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(customerDto.getFirstName(),customerDto.getLastName(),customerDto.getAddress(),customerDto.getPhoneNumber(),customerDto.getCustomerId()));
    }

    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id);
    }

    public List<CustomerDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto>customerDTOS= new ArrayList<>();
        List<Customer>customers = customerDAO.getAll();
        for (Customer customer : customers){
            customerDTOS.add(new CustomerDto(customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getPhoneNumber(),customer.getCustomerId()));
        }
        return customerDTOS;
    }

    public CustomerDto search(String customerSearch) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(customerSearch);
        if (customer != null) {
            return new CustomerDto(customer.getFirstName(),customer.getLastName(),customer.getAddress(),customer.getPhoneNumber(),customer.getCustomerId());
        } else {
            return null;
        }
    }
}
