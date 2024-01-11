package lk.ijse.rms.dao.custom.impl;

import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.ItemDAO;
import lk.ijse.rms.db.DbConnection;
import lk.ijse.rms.dto.ItemDto;
import lk.ijse.rms.dto.tm.OrderCartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(ItemDto itemDto) throws SQLException, ClassNotFoundException {

        return SQLUtil.execute("INSERT INTO item VALUES (?,?)",itemDto.getItemId(),itemDto.getType());
    }

    @Override
    public boolean update(ItemDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    public List<ItemDto> getAll() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item");

        ArrayList<ItemDto> dtoList = new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new ItemDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3)

                    )
            );
        }
        return dtoList;
    }

    public List<ItemDto> getAllItemId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item");

        ArrayList<ItemDto> dtoList = new ArrayList<>();
        while(resultSet.next()) {
            dtoList.add(
                    new ItemDto(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getInt(3)

                    )
            );
        }
        return dtoList;
    }

    public ItemDto search(String type) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM item WHERE type= ?",type);

        ItemDto itemDto = null;
        if (resultSet.next()){
            itemDto = new ItemDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3)
            );
        }
        return itemDto;

    }


    public boolean updateItem(List<OrderCartTm> orderCartTmList) throws SQLException, ClassNotFoundException {
        for(OrderCartTm tm : orderCartTmList) {
            System.out.println("Item: " + tm);
            if(!updateItemCount(tm.getItemId(),tm.getQty())) {
                return false;
            }
        }
        return true;
    }

    public boolean updateItemCount(String itemId, int count) throws SQLException, ClassNotFoundException {
//        Connection connection = DbConnection.getInstance().getConnection();
//
//        PreparedStatement pstm = connection.prepareStatement("UPDATE item SET count = count + ? where itemId = ? ");
//
//        pstm.setInt(1, count);
//        pstm.setString(2, itemId);
//
//        return pstm.executeUpdate() > 0;

        return SQLUtil.execute("UPDATE item SET count = count + ? where itemId = ? ",itemId,count);

    }
}
