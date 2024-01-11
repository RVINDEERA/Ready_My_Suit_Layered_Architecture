package lk.ijse.rms.bo.custom;

import lk.ijse.rms.bo.SupperBO;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dto.FabricDto;
import lk.ijse.rms.dto.ItemDto;
import lk.ijse.rms.dto.tm.OrderCartTm;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBo extends SupperBO {
    public String generateNextID() throws SQLException, ClassNotFoundException ;
    public boolean delete(String id) throws SQLException, ClassNotFoundException;
    public ItemDto search(String id) throws SQLException, ClassNotFoundException ;
    public List<ItemDto> getAll() throws SQLException, ClassNotFoundException;
    public List<ItemDto> getAllItemId() throws SQLException, ClassNotFoundException;
    public boolean updateItem(List<OrderCartTm> orderCartTmList) throws SQLException, ClassNotFoundException;
}
