package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.ShirtDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ShirtMeasurementBo extends SupperBO {
    public boolean save(ShirtDto shirtDto) throws SQLException, ClassNotFoundException;
    public String generateNextID() throws SQLException, ClassNotFoundException ;
    public String splitShirtID(String currentShirtID);
    public ShirtDto search(String shirtM) throws SQLException, ClassNotFoundException ;
    public boolean update(ShirtDto shirtDto) throws SQLException, ClassNotFoundException;

}
