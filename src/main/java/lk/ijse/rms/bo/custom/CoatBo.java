package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.dto.tm.CartTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CoatBo extends SupperBO {
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public boolean save(CoatDto coatDto) throws SQLException, ClassNotFoundException;
    public String generateNextID() throws SQLException, ClassNotFoundException;
    public String splitCustomerID(String currentCoatID);
    public boolean update(CoatDto coatDto) throws SQLException, ClassNotFoundException;
    public boolean updateCoat(List<CartTm> cartTmList) throws SQLException, ClassNotFoundException;
    public CoatDto search(String id) throws SQLException, ClassNotFoundException;
    public List<CoatDto> getAll() throws SQLException, ClassNotFoundException;

    public List<CoatDto> loadAllItems() throws SQLException, ClassNotFoundException;
}
