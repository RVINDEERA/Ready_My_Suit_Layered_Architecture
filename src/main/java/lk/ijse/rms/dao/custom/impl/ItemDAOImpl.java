package lk.ijse.rms.dao.custom.impl;

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
    public boolean saveItem(ItemDto itemDto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO item VALUES (?,?)");

        pstm.setString(1,itemDto.getItemId());
        pstm.setString(2,itemDto.getType());

        return  pstm.executeUpdate() > 0;
    }


    public List<ItemDto> getAllTypes() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

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

    public List<ItemDto> getAllItemId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM item";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

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

    public ItemDto searchItemId(String type) throws SQLException {
        Connection connection=DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM item WHERE type= ?");

        pstm.setString(1,type);

        ResultSet resultSet = pstm.executeQuery();
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


    public boolean updateItem(List<OrderCartTm> orderCartTmList) throws SQLException {
        for(OrderCartTm tm : orderCartTmList) {
            System.out.println("Item: " + tm);
            if(!updateItemCount(tm.getItemId(),tm.getQty())) {
                return false;
            }
        }
        return true;
    }

    public boolean updateItemCount(String itemId, int count) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement("UPDATE item SET count = count + ? where itemId = ? ");

        pstm.setInt(1, count);
        pstm.setString(2, itemId);

        return pstm.executeUpdate() > 0;

    }
}
