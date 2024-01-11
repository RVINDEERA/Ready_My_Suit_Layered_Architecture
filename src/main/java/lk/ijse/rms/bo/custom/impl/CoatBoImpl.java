package lk.ijse.rms.bo.custom.impl;

import lk.ijse.rms.bo.custom.CoatBo;
import lk.ijse.rms.dao.DAOFactory;
import lk.ijse.rms.dao.SQLUtil;
import lk.ijse.rms.dao.custom.CoatDAO;
import lk.ijse.rms.dto.CoatDto;
import lk.ijse.rms.dto.tm.CartTm;
import lk.ijse.rms.entity.Coat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoatBoImpl implements CoatBo {

    CoatDAO coatDAO = (CoatDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COAT);
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return coatDAO.delete(id);
    }

    public boolean save(CoatDto coatDto) throws SQLException, ClassNotFoundException {
        return coatDAO.save(new Coat(coatDto.getCoatId(),coatDto.getType(),coatDto.getColor(),coatDto.getAvailability(),coatDto.getDate(),coatDto.getPrice(),coatDto.getSize()));
    }

    public String generateNextID() throws SQLException, ClassNotFoundException {
      return coatDAO.generateNextID();
    }

    public String splitCustomerID(String currentCoatID) {
        if (currentCoatID != null){
            String [] split = currentCoatID.split("(COT)");

            int id = Integer.parseInt(split[1]);
            id++;
            return String.format("COT%03d", id);
        }else {
            return "COT001";
        }
    }

    public boolean update(CoatDto coatDto) throws SQLException, ClassNotFoundException {
        return coatDAO.update(new Coat(coatDto.getType(),coatDto.getColor(),coatDto.getAvailability(),coatDto.getDate(),coatDto.getPrice(),coatDto.getSize(),coatDto.getCoatId()));
    }

    public boolean updateCoat(List<CartTm>cartTmList) throws SQLException, ClassNotFoundException {
        return coatDAO.updateCoat(cartTmList);
    }


    public CoatDto search(String id) throws SQLException, ClassNotFoundException {
        Coat coat = coatDAO.search(id);
        if (coat !=null){
            return new CoatDto(coat.getCoatId(),coat.getType(),coat.getColor(),coat.getAvailability(),coat.getDate(),coat.getPrice(),coat.getSize());
        }else {

            return null;
        }
    }

    public List<CoatDto> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<CoatDto> coatDto = new ArrayList<>();
        List<Coat>coats =coatDAO.getAll();

      for (Coat coat : coats){
          coatDto.add(new CoatDto(coat.getCoatId(),coat.getType(),coat.getColor(),coat.getAvailability(),coat.getDate(),coat.getPrice(),coat.getSize()));
      }
      return coatDto;
    }

    public List<CoatDto> loadAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<CoatDto> coatDto = new ArrayList<>();
        List<Coat>coats =coatDAO.loadAllItems();

          for (Coat coat : coats){
            coatDto.add(new CoatDto(coat.getCoatId(),coat.getType(),coat.getColor(),coat.getAvailability(),coat.getDate(),coat.getPrice(),coat.getSize()));
        }
          return coatDto;
    }
}
