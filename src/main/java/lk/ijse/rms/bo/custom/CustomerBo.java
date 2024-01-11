package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.CustomerDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CustomerBo extends SupperBO {
    public boolean save (CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public CustomerDto searchCustomer(String customerPhone ) throws SQLException, ClassNotFoundException;
    public String generateNextID() throws SQLException, ClassNotFoundException;
    public boolean update(CustomerDto customerDto) throws SQLException, ClassNotFoundException;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;

    public List<CustomerDto> getAll() throws SQLException, ClassNotFoundException;

    public CustomerDto search(String customerSearch) throws SQLException, ClassNotFoundException;

}
