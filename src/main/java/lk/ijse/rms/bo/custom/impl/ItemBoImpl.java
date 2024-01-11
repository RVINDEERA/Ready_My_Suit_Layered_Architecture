package lk.ijse.rms.bo.custom.impl;

import lk.ijse.rms.bo.custom.ItemBo;
import lk.ijse.rms.dao.DAOFactory;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.ItemDAO;
import lk.ijse.rms.dto.ItemDto;
import lk.ijse.rms.dto.tm.OrderCartTm;
import lk.ijse.rms.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBoImpl implements ItemBo {
    
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    
    @Override
    public String generateNextID() throws SQLException, ClassNotFoundException {
        return null;
    }

    public boolean save(ItemDto itemDto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(itemDto.getItemId(),itemDto.getType(),itemDto.getCount()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }


    public List<ItemDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> itemDtos= new ArrayList<>();
        List<Item> items = itemDAO.getAll();
        for (Item item : items){
            itemDtos.add(new ItemDto(item.getItemId(),item.getType(),item.getCount()));
        }
        return itemDtos;
    }

    public List<ItemDto> getAllItemId() throws SQLException, ClassNotFoundException {
        ArrayList<ItemDto> itemDtos= new ArrayList<>();
        List<Item> items = itemDAO.getAllItemId();
        for (Item item : items){
            itemDtos.add(new ItemDto(item.getItemId(),item.getType(),item.getCount()));
        }
        return itemDtos;
    }

    public ItemDto search(String type) throws SQLException, ClassNotFoundException {
        Item item = itemDAO.search(type);
        if (item != null) {
            return new ItemDto(item.getItemId(),item.getType(), item.getCount());
        } else {
            return null;
        }
    }


    public boolean updateItem(List<OrderCartTm> orderCartTmList) throws SQLException, ClassNotFoundException {
        return itemDAO.updateItem(orderCartTmList);
    }
}
