package lk.ijse.rms.dao.custom;


import lk.ijse.rms.dto.ShirtDto;
import lk.ijse.rms.entity.Shirt;


import java.sql.SQLException;

public interface ShirtMeasurementDAO extends CrudDAO<Shirt> {
   // boolean shirtMeasurementSave(ShirtDto shirtDto) throws SQLException, ClassNotFoundException;
    //String generateNextShirtId() throws SQLException, ClassNotFoundException;
    String splitShirtID(String currentShirtID);
   // ShirtDto searchMeasurements(String shirtM) throws SQLException, ClassNotFoundException;
    //boolean updateShirt(ShirtDto shirtDto) throws SQLException, ClassNotFoundException;
}
