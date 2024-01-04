package lk.ijse.rms.dao.custom;

import lk.ijse.rms.dto.ItemDto;
import lk.ijse.rms.dto.tm.OrderCartTm;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO {
    boolean saveItem(ItemDto itemDto) throws SQLException ;
    List<ItemDto> getAllTypes() throws SQLException ;
    List<ItemDto> getAllItemId() throws SQLException ;
    ItemDto searchItemId(String type) throws SQLException ;
    boolean updateItem(List<OrderCartTm> orderCartTmList) throws SQLException ;
    boolean updateItemCount(String itemId, int count) throws SQLException ;
}
