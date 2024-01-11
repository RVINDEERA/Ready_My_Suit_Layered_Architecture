package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.ItemDto;
import lk.ijse.rms.dto.tm.OrderCartTm;
import lk.ijse.rms.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item> {
    List<Item> getAllItemId() throws SQLException, ClassNotFoundException;
    boolean updateItem(List<OrderCartTm> orderCartTmList) throws SQLException, ClassNotFoundException;
    boolean updateItemCount(String itemId, int count) throws SQLException, ClassNotFoundException;
}
